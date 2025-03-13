import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void printTasks(List<String> taskList) {
        System.out.println("Ваш список дел:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + ". " + taskList.get(i));
        }
    }

    public static int searchTaskIndex(List<String> taskList, String task) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).equals(task)) {
                return i;
            }
        }
        return -1;
    }

    public static int searchTaskMatchIndex(List<String> taskList, String keyword) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).contains(keyword)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean removeMatch(List<String> taskList, String keyword) {
        int removedCount = 0;
        int i = searchTaskMatchIndex(taskList, keyword);
        while (i >= 0) {
            taskList.remove(i);
            removedCount++;
            i = searchTaskMatchIndex(taskList, keyword);
        }
        if (removedCount > 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        List<String> taskList = new ArrayList<>();
        taskList.add("Сделать уборку");
        taskList.add("Сходить в магазин стройматериалов");
        taskList.add("Сходить в обувной магазин");
        taskList.add("Отнести колонки в ремонт");
        taskList.add("Отправить посылку");

        System.out.println("Здравствуйте! Давайте поработаем над вашим списком дел!");
        System.out.println("Вчера вы уже запланировали несколько задач.");
        printTasks(taskList);

        Scanner scanner = new Scanner(System.in);
        String oper;
        boolean result;
        while (true) {
            System.out.println();
            System.out.println("Выберите операцию: ");
            System.out.println("0 - Выход из программы");
            System.out.println("1 - Добавить дело");
            System.out.println("2 - Показать дела");
            System.out.println("3 - Удалить дело по номеру");
            System.out.println("4 - Удалить дело по названию");
            System.out.println("5 - Удалить дело по ключевому слову");
            System.out.print("Ваш выбор: ");
            oper = scanner.nextLine();

            if (oper.equals("0")) {
                break;
            }

            int operation;
            try {
                operation = Integer.parseInt(oper);
            } catch (NumberFormatException e) {
                System.out.println("Неверный выбор, попробуйте еще раз");
                continue;
            }

            switch (operation) {
                case 1:
                    System.out.println();
                    System.out.print("Введите название задачи: ");
                    oper = scanner.nextLine();
                    taskList.add(oper);
                    System.out.println("Добавлено!");

                    printTasks(taskList);
                    break;
                case 2:
                    System.out.println();
                    printTasks(taskList);
                    break;
                case 3:
                    System.out.println();
                    System.out.print("Введите номер для удаления: ");
                    oper = scanner.nextLine();
                    try {
                        operation = Integer.parseInt(oper);
                    } catch (NumberFormatException e) {
                        System.out.println("Здесь нужен номер задачи, попробуйте еще раз");
                        break;
                    }
                    if (operation > taskList.size()) {
                        System.out.println("Задачи с таким номером нет, удалять нечего");
                        break;
                    } else {
                        taskList.remove(operation - 1);
                        System.out.println("Удалено!");
                    }
                    printTasks(taskList);
                    break;
                case 4:
                    System.out.println();
                    System.out.print("Введите задачу для удаления: ");
                    oper = scanner.nextLine();
                    operation = searchTaskIndex(taskList, oper);
                    if (operation == -1) {
                        System.out.println("Такой задачи нет, попробуйте еще раз");
                    } else {
                        taskList.remove(operation);
                        System.out.println("Удалено!");
                    }
                    printTasks(taskList);
                    break;
                case 5:
                    System.out.println();
                    System.out.print("Введите слово для удаления: ");
                    oper = scanner.nextLine();
                    result = removeMatch(taskList, oper);
                    if (result) {
                        System.out.println("Удалено!");
                    } else {
                        System.out.println("Не нашлось задач с таким ключевым словом");
                    }
                    printTasks(taskList);
                    break;
                default:
                    System.out.println("Такой операции нет");
            }
        }

        System.out.println();
        System.out.println("Ваш финальный список дел на сегодня:");
        printTasks(taskList);
    }
}
