package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import javax.naming.Name;
import javax.swing.text.DateFormatter;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Program {
    public static void main(String[] args) throws ParseException {
        
        Locale.setDefault(Locale.US);
        Scanner tec = new Scanner(System.in);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Enter the department's name: ");
        String departmentName = tec.nextLine();

        System.out.println("Enter the worker data: ");
        System.out.print("Name: ");
        String workerName = tec.nextLine();

        System.out.print("Level: ");
        String workerLevel = tec.nextLine();

        System.out.print("Base salary: ");
        Double baseSalary = Double.parseDouble(tec.nextLine());

        // Objeto do tipo Worker instanciado
        // departamento é um objeto instanciado porque na classe worker, há um departamento
        Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), baseSalary, new Department(departmentName));

        System.out.println("How many contract to this worker? ");
        int n = Integer.parseInt(tec.nextLine());

        for (int i=1; i<=n; i++){
            System.out.println("Enter contract #" + i + " data: ");
            System.out.print("Date (DD/MM/YYYY): ");
            Date contractDate = sdf.parse(tec.nextLine());

            System.out.print("Value per hour: ");
            Double valuePerHour = Double.parseDouble(tec.nextLine());

            System.out.print("Duration (hours): ");
            int hours = Integer.parseInt(tec.nextLine());

            HourContract contract = new HourContract(contractDate, valuePerHour, hours);
            worker.addContract(contract);
        }

        System.out.print("Enter month and year to calculate income (MM/YYYY): ");
        String monthAndYear = tec.nextLine();
        int month = Integer.parseInt(monthAndYear.substring(0, 2));
        int year = Integer.parseInt(monthAndYear.substring(3));

        System.out.println("Name: " + worker.getName());
        System.out.println("Departmanet: " + worker.getDepartment().getName());
        System.out.println("Income for " + monthAndYear + ": " + String.format("%.2f", worker.income(year, month)));


        tec.close();
    }
}
