import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void printTasks(LinkedList<String> taskList) {
        int i = 0;
        System.out.println("Ваш список дел:");
        for (String t : taskList) {
            i++;
            System.out.println(i + ". " + t);
        }
    }

    public static int searchTaskIndex(LinkedList<String> taskList, String task) {
        int i = 0;
        for (String t : taskList) {
            if(t.equals(task)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int searchTaskMatchIndex(LinkedList<String> taskList, String keyword) {
        int i = 0;
        for (String t : taskList) {
            if(t.contains(keyword)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static boolean removeMatch(LinkedList<String> taskList, String keyword) {
        int i = 0;
        int n = 0;
        i = searchTaskMatchIndex(taskList, keyword);
        while (i >= 0) {
            taskList.remove(i);
            n++;
            i = searchTaskMatchIndex(taskList, keyword);
        }
        if (n > 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        LinkedList<String> taskList = new LinkedList<>();
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
            System.out.print  ("Ваш выбор: ");
            oper = scanner.nextLine();

            if (oper.equals("0")) {
                break;
            }

            int operation;
            try {
                operation = Integer.parseInt(oper);
            } catch (NumberFormatException e) {
                System.out.println ("Неверный выбор, попробуйте еще раз");
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
                        System.out.println ("Здесь нужен номер задачи, попробуйте еще раз");
                        break;
                    }
                    if (operation > taskList.size()) {
                        System.out.println ("Задачи с таким номером нет, удалять нечего");
                        break;
                    }
                    else {
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
                        System.out.println ("Такой задачи нет, попробуйте еще раз");
                    }
                    else {
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
                    }
                    else {
                        System.out.println ("Не нашлось задач с таким ключевым словом");
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
