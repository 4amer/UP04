import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

abstract class Ammunition {
    private String name;
    private double price;
    private double weight;

    public Ammunition(String name, double price, double weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return name + ": Цена = " + price + ", Вес = " + weight;
    }
}

class Helmet extends Ammunition {
    public Helmet(double price, double weight) {
        super("Шлем", price, weight);
    }
}

class Jacket extends Ammunition {
    public Jacket(double price, double weight) {
        super("Куртка", price, weight);
    }
}

class Gloves extends Ammunition {
    public Gloves(double price, double weight) {
        super("Перчатки", price, weight);
    }
}

class Boots extends Ammunition {
    public Boots(double price, double weight) {
        super("Ботинки", price, weight);
    }
}

class Motorcyclist {
    private List<Ammunition> equipment = new ArrayList<>();

    public void equip(Ammunition item) {
        equipment.add(item);
    }

    public double calculateTotalCost() {
        double totalCost = 0;
        for (Ammunition item : equipment) {
            totalCost += item.getPrice();
        }
        return totalCost;
    }

    public void sortEquipmentByWeight() {
        Collections.sort(equipment, Comparator.comparingDouble(Ammunition::getWeight));
    }

    public List<Ammunition> findEquipmentByPriceRange(double minPrice, double maxPrice) {
        List<Ammunition> result = new ArrayList<>();
        for (Ammunition item : equipment) {
            if (item.getPrice() >= minPrice && item.getPrice() <= maxPrice) {
                result.add(item);
            }
        }
        return result;
    }

    public void displayEquipment() {
        for (Ammunition item : equipment) {
            System.out.println(item);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Motorcyclist motorcyclist = new Motorcyclist();
        Scanner scanner = new Scanner(System.in);

        // Примерная амуниция
        motorcyclist.equip(new Helmet(150.0, 1.5));
        motorcyclist.equip(new Jacket(300.0, 3.0));
        motorcyclist.equip(new Gloves(50.0, 0.5));
        motorcyclist.equip(new Boots(200.0, 2.0));

        boolean exit = false;
        while (!exit) {
            System.out.println("\nМеню:");
            System.out.println("1. Показать экипировку");
            System.out.println("2. Посчитать общую стоимость экипировки");
            System.out.println("3. Отсортировать экипировку по весу");
            System.out.println("4. Найти экипировку по диапазону цены");
            System.out.println("5. Выход");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Экипировка мотоциклиста:");
                    motorcyclist.displayEquipment();
                    break;
                case 2:
                    System.out.println("Общая стоимость экипировки: " + motorcyclist.calculateTotalCost());
                    break;
                case 3:
                    motorcyclist.sortEquipmentByWeight();
                    System.out.println("Экипировка отсортирована по весу.");
                    motorcyclist.displayEquipment();
                    break;
                case 4:
                    System.out.print("Введите минимальную цену: ");
                    double minPrice = scanner.nextDouble();
                    System.out.print("Введите максимальную цену: ");
                    double maxPrice = scanner.nextDouble();
                    List<Ammunition> foundItems = motorcyclist.findEquipmentByPriceRange(minPrice, maxPrice);
                    if (foundItems.isEmpty()) {
                        System.out.println("Экипировка в данном диапазоне не найдена.");
                    } else {
                        System.out.println("Экипировка в диапазоне цены:");
                        for (Ammunition item : foundItems) {
                            System.out.println(item);
                        }
                    }
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }

        scanner.close();
    }
}
