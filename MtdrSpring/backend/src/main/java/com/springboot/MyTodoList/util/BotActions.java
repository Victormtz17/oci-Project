package com.springboot.MyTodoList.util;

import com.springboot.MyTodoList.model.Task;
import com.springboot.MyTodoList.service.DeepSeekService;
import com.springboot.MyTodoList.service.TaskService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class BotActions {

    private static final Logger logger = LoggerFactory.getLogger(BotActions.class);
    private static final Map<Long, Integer> pendingDoneByChat = new ConcurrentHashMap<>();
    private static final String IN_PROGRESS_TAG = "[IN_PROGRESS]";

    // Default user ID used when creating tasks via Telegram bot
    private static final int BOT_USER_ID = 1;

    String requestText;
    long chatId;
    TelegramClient telegramClient;
    boolean exit;
    String activeSprint;

    TaskService taskService;
    DeepSeekService deepSeekService;

    public BotActions(TelegramClient tc, TaskService ts, DeepSeekService ds, String sprint) {
        telegramClient = tc;
        taskService = ts;
        deepSeekService = ds;
        activeSprint = sprint;
        exit = false;
    }

    public void setRequestText(String cmd) {
        requestText = cmd;
    }

    public void setChatId(long chId) {
        chatId = chId;
    }

    public void setTelegramClient(TelegramClient tc) {
        telegramClient = tc;
    }

    public void setTaskService(TaskService tsvc) {
        taskService = tsvc;
    }

    public TaskService getTaskService() {
        return taskService;
    }

    public void setDeepSeekService(DeepSeekService dssvc) {
        deepSeekService = dssvc;
    }

    public DeepSeekService getDeepSeekService() {
        return deepSeekService;
    }

    public void fnStart() {
        if (!(requestText.equals(BotCommands.START_COMMAND.getCommand()) || requestText.equals(BotLabels.SHOW_MAIN_SCREEN.getLabel())) || exit)
            return;

        BotHelper.sendMessageToTelegram(chatId, BotMessages.HELLO_MYTODO_BOT.getMessage(), telegramClient,
            ReplyKeyboardMarkup
                .builder()
                .keyboardRow(new KeyboardRow(BotLabels.LIST_ALL_ITEMS.getLabel(), BotLabels.ADD_NEW_ITEM.getLabel()))
                .keyboardRow(new KeyboardRow(BotLabels.SHOW_MAIN_SCREEN.getLabel(), BotLabels.HIDE_MAIN_SCREEN.getLabel()))
                .build()
        );
        exit = true;
    }

    public void fnDone() {
        if (exit)
            return;

        String normalized = requestText == null ? "" : requestText.trim().toUpperCase();
        if (!normalized.contains(BotLabels.DONE.getLabel()))
            return;
        if (!requestText.contains(BotLabels.DASH.getLabel()))
            return;

        String done = requestText.substring(0, requestText.indexOf(BotLabels.DASH.getLabel()));
        Integer id = Integer.valueOf(done);

        try {
            Task item = taskService.getTaskByIdSimple(id);
            if (item == null) {
                BotHelper.sendMessageToTelegram(chatId, BotMessages.ITEM_DELETED.getMessage(), telegramClient);
                exit = true;
                return;
            }
            pendingDoneByChat.put(chatId, id);
            BotHelper.sendMessageToTelegram(chatId, BotMessages.ASK_COMPLETION_HOURS.getMessage(), telegramClient);

        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
        }
        exit = true;
    }

    public void fnCompleteTask() {
        if (exit) {
            return;
        }

        Integer pendingTaskId = pendingDoneByChat.get(chatId);
        if (pendingTaskId == null) {
            return;
        }

        double actualHours;
        try {
            actualHours = Double.parseDouble(requestText.trim());
            if (actualHours <= 0) {
                throw new NumberFormatException("actualHours must be greater than zero");
            }
        } catch (NumberFormatException ex) {
            BotHelper.sendMessageToTelegram(chatId, BotMessages.INVALID_COMPLETION_HOURS.getMessage(), telegramClient);
            exit = true;
            return;
        }

        try {
            Task completedItem = taskService.completeTask(pendingTaskId, actualHours);
            if (completedItem == null) {
                BotHelper.sendMessageToTelegram(chatId, BotMessages.ITEM_DELETED.getMessage(), telegramClient);
            } else {
                BotHelper.sendMessageToTelegram(chatId, BotMessages.ITEM_DONE.getMessage(), telegramClient);
            }
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
        } finally {
            pendingDoneByChat.remove(chatId);
        }
        exit = true;
    }

    public void fnSprint() {
        if (exit) {
            return;
        }

        String normalized = requestText == null ? "" : requestText.trim().toUpperCase();
        if (!normalized.contains(BotLabels.SPRINT.getLabel())) {
            return;
        }
        if (!requestText.contains(BotLabels.DASH.getLabel())) {
            return;
        }

        String sprintAction = requestText.substring(0, requestText.indexOf(BotLabels.DASH.getLabel()));
        Integer id = Integer.valueOf(sprintAction);

        try {
            Task item = taskService.getTaskByIdSimple(id);
            if (item == null) {
                BotHelper.sendMessageToTelegram(chatId, BotMessages.ITEM_DELETED.getMessage(), telegramClient);
                exit = true;
                return;
            }

            String normalizedTitle = normalizeDescription(item.getTitle());
            String updatedTitle = normalizedTitle + " " + IN_PROGRESS_TAG + " " + getSprintTag();
            item.setTitle(updatedTitle.trim());
            item.setStatus("IN_PROGRESS");
            item.setTaskStage("SPRINT");
            taskService.updateTask(id, item);
            BotHelper.sendMessageToTelegram(chatId, BotMessages.ITEM_ASSIGNED_SPRINT.getMessage(), telegramClient);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
        }
        exit = true;
    }

    public void fnUndo() {
        if (requestText.indexOf(BotLabels.UNDO.getLabel()) == -1 || exit)
            return;

        String undo = requestText.substring(0, requestText.indexOf(BotLabels.DASH.getLabel()));
        Integer id = Integer.valueOf(undo);

        try {
            Task item = taskService.getTaskByIdSimple(id);
            if (item != null) {
                item.setStatus("PENDING");
                item.setTaskStage("BACKLOG");
                item.setActualHours(null);
                taskService.updateTask(id, item);
            }
            BotHelper.sendMessageToTelegram(chatId, BotMessages.ITEM_UNDONE.getMessage(), telegramClient);

        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
        }
        exit = true;
    }

    public void fnDelete() {
        if (requestText.indexOf(BotLabels.DELETE.getLabel()) == -1 || exit)
            return;

        String delete = requestText.substring(0, requestText.indexOf(BotLabels.DASH.getLabel()));
        Integer id = Integer.valueOf(delete);

        try {
            taskService.deleteTask(id);
            BotHelper.sendMessageToTelegram(chatId, BotMessages.ITEM_DELETED.getMessage(), telegramClient);

        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
        }
        exit = true;
    }

    public void fnHide() {
        if (requestText.equals(BotCommands.HIDE_COMMAND.getCommand())
                || requestText.equals(BotLabels.HIDE_MAIN_SCREEN.getLabel()) && !exit)
            BotHelper.sendMessageToTelegram(chatId, BotMessages.BYE.getMessage(), telegramClient);
        else
            return;
        exit = true;
    }

    public void fnListAll() {
        if (!(requestText.equals(BotCommands.TODO_LIST.getCommand())
                || requestText.equals(BotLabels.LIST_ALL_ITEMS.getLabel())
                || requestText.equals(BotLabels.MY_TODO_LIST.getLabel())) || exit)
            return;

        logger.info("taskService: " + taskService);
        List<Task> allItems = taskService.findAll();
        ReplyKeyboardMarkup keyboardMarkup = ReplyKeyboardMarkup.builder()
            .resizeKeyboard(true)
            .oneTimeKeyboard(false)
            .selective(true)
            .build();

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow mainScreenRowTop = new KeyboardRow();
        mainScreenRowTop.add(BotLabels.SHOW_MAIN_SCREEN.getLabel());
        keyboard.add(mainScreenRowTop);

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add(BotLabels.ADD_NEW_ITEM.getLabel());
        keyboard.add(firstRow);

        KeyboardRow myTodoListTitleRow = new KeyboardRow();
        myTodoListTitleRow.add(BotLabels.MY_TODO_LIST.getLabel());
        keyboard.add(myTodoListTitleRow);

        List<Task> activeItems = allItems.stream()
                .filter(item -> !"DONE".equals(item.getStatus()))
                .collect(Collectors.toList());

        for (Task item : activeItems) {
            KeyboardRow currentRow = new KeyboardRow();
            currentRow.add(item.getTitle());
            currentRow.add(item.getTaskId() + BotLabels.DASH.getLabel() + BotLabels.DONE.getLabel());
            currentRow.add(item.getTaskId() + BotLabels.DASH.getLabel() + BotLabels.SPRINT.getLabel());
            keyboard.add(currentRow);
        }

        List<Task> doneItems = allItems.stream()
                .filter(item -> "DONE".equals(item.getStatus()))
                .collect(Collectors.toList());

        for (Task item : doneItems) {
            KeyboardRow currentRow = new KeyboardRow();
            currentRow.add(item.getTitle());
            currentRow.add(item.getTaskId() + BotLabels.DASH.getLabel() + BotLabels.UNDO.getLabel());
            currentRow.add(item.getTaskId() + BotLabels.DASH.getLabel() + BotLabels.DELETE.getLabel());
            keyboard.add(currentRow);
        }

        KeyboardRow mainScreenRowBottom = new KeyboardRow();
        mainScreenRowBottom.add(BotLabels.SHOW_MAIN_SCREEN.getLabel());
        keyboard.add(mainScreenRowBottom);

        keyboardMarkup.setKeyboard(keyboard);
        BotHelper.sendMessageToTelegram(chatId, BotLabels.MY_TODO_LIST.getLabel(), telegramClient, keyboardMarkup);
        exit = true;
    }

    public void fnAddItem() {
        logger.info("Adding item");
        if (!(requestText.contains(BotCommands.ADD_ITEM.getCommand())
                || requestText.contains(BotLabels.ADD_NEW_ITEM.getLabel())) || exit)
            return;
        logger.info("Adding item by BotHelper");
        BotHelper.sendMessageToTelegram(chatId, BotMessages.TYPE_NEW_TODO_ITEM.getMessage(), telegramClient);
        exit = true;
    }

    public void fnElse() {
        if (exit)
            return;

        String normalized = requestText == null ? "" : requestText.trim().toUpperCase();
        if (normalized.startsWith("/")) {
            return;
        }
        if (normalized.contains(BotLabels.DASH.getLabel())
            && (normalized.contains(BotLabels.DONE.getLabel())
                || normalized.contains(BotLabels.UNDO.getLabel())
                || normalized.contains(BotLabels.DELETE.getLabel())
                || normalized.contains(BotLabels.SPRINT.getLabel()))) {
            return;
        }

        Task newItem = new Task();
        newItem.setTitle(requestText);
        newItem.setCreatedAt(LocalDateTime.now());
        newItem.setStatus("PENDING");
        newItem.setTaskStage("BACKLOG");
        newItem.setCreatedBy(BOT_USER_ID);
        newItem.setIsDeleted("N");
        taskService.addTask(newItem);

        BotHelper.sendMessageToTelegram(chatId, BotMessages.NEW_ITEM_ADDED.getMessage(), telegramClient, null);
    }

    public void fnLLM() {
        logger.info("Calling LLM");
        if (!(requestText.contains(BotCommands.LLM_REQ.getCommand())) || exit)
            return;

        String prompt = "Dame los datos del clima en mty";
        String out = "<empty>";
        try {
            out = deepSeekService.generateText(prompt);
        } catch (Exception exc) {
            logger.error(exc.getLocalizedMessage(), exc);
        }

        BotHelper.sendMessageToTelegram(chatId, "LLM: " + out, telegramClient, null);
    }

    private String getSprintTag() {
        String sprintName = activeSprint;
        if (sprintName == null || sprintName.trim().isEmpty()) {
            sprintName = "Sprint-Default";
        }
        return "[SPRINT:" + sprintName.trim() + "]";
    }

    private String normalizeDescription(String title) {
        if (title == null) {
            return "";
        }
        String result = title.replaceAll("\\[SPRINT:[^\\]]*\\]", "");
        result = result.replace(IN_PROGRESS_TAG, "");
        return result.trim();
    }
}
