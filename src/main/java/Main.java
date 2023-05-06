import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.Students;


import java.util.List;
public class Main {
    public static void main(String[] args) {
        // Создаем экземпляр EntityManagerFactory, указывая persistence unit
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");

        // Создаем экземпляр EntityManager из EntityManagerFactory
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Начинаем транзакцию
        entityManager.getTransaction().begin();

        // Создаем JPQL-запрос для выборки студентов с возрастом больше minAge
        String jpqlQuery = "SELECT s FROM Students s WHERE s.age > :minAge";
        // Создаем объект запроса с указанием типа возвращаемого результата (Student.class)
        TypedQuery<Students> query = entityManager.createQuery(jpqlQuery, Students.class);

        // Устанавливаем значение параметра minAge в запросе
          query.setParameter("minAge", 1);

        // Выполняем запрос и получаем результат в виде списка студентов
        List<Students> students = query.getResultList();

        // Завершаем транзакцию
        entityManager.getTransaction().commit();

        // Выводим информацию о студентах в консоль
        for (Students student : students) {
            System.out.println("Student ID: " + student.getId());
            System.out.println("Student Name: " + student.getName());
            System.out.println("Student Age: " + student.getAge());
            System.out.println("------------");
        }

        // Закрываем EntityManager и EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}
