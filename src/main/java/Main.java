import entity.PersonEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main
{
    private static Scanner scanner = new Scanner(System.in);
    private static EntityManagerFactory fac = Persistence.createEntityManagerFactory("default");
    private static EntityManager man = fac.createEntityManager();
    private static EntityTransaction tr = man.getTransaction();

    public static void main(String[] args)
    {
        while (true)
        {
            try
            {
                System.out.println("Wybierz opcje");
                System.out.println("0. Wyjdz");
                System.out.println("1. Dodaj");

                Integer option = scanner.nextInt();
                switch (option)
                {
                    case 0:
                        man.close();
                        fac.close();
                        System.exit(0);
                    case 1:
                        String name = getName();
                        String surname = getSurname();
                        String email = getEmail();
                        addPerson(name, surname, email);
                        break;
                    default:
                        System.out.println("Nie ma takiej opcji!");
                        break;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error");
                System.exit(1);
            }
        }
    }

    public static String getName()
    {
        System.out.println("Wpisz imie:");
        String name = scanner.nextLine();
        return name;
    }

    public static String getSurname()
    {
        System.out.println("Wpisz nazwisko:");
        String surname = scanner.nextLine();
        return surname;
    }

    public static String getEmail()
    {
        System.out.println("Wpisz maila:");
        String email = scanner.nextLine();
        return email;
    }

    public static void addPerson(String name, String surname, String email)
    {
        try
        {
            tr.begin();

            PersonEntity person = new PersonEntity();
            person.setpName(name);
            person.setpSurname(surname);
            person.setpEmail(email);

            man.persist(person);

            tr.commit();
        }
        finally
        {
            if (tr.isActive()) tr.rollback();
            else System.out.println("Osoba została dodana");
        }
    }

}

