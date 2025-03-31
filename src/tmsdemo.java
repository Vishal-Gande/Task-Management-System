import java.util.Date;

public class tmsdemo {
    public static void main(String[] args) {

        TaskManagementSystem tms = new TaskManagementSystem();

        User u1 = new User(1, "vish", "avc@gmail.com", "password");
        User u2 = new User(2, "prav", "pra@gmail.com", "password");
        User u3 = new User(3, "neet", "neet@gmail.com", "password");

        tms.addUser(u1);
        tms.addUser(u2);
        tms.addUser(u3);

        Task t1 = new Task(1,"get milk","Go to supermarket and buy milk",
                TaskStatus.NOT_STARTED, 1, new Date(),1, TaskPriority.MEDIUM, u1);


        Task t2 = new Task(2,"cc bill","Go to cred and pay bill",
                TaskStatus.NOT_STARTED, 1, new Date(),1, TaskPriority.HIGH, u1);

        Task t3 = new Task(3,"book gas","order bharat gas",
                TaskStatus.NOT_STARTED, 1, new Date(),1, TaskPriority.HIGH, u1);

        Task t4 = new Task(4,"plan goa","checkout mmt",
                TaskStatus.NOT_STARTED, 1, new Date(),1, TaskPriority.LOW, u1);


        tms.addTask(t1);
        tms.addTask(t2);
        tms.addTask(t3);
        tms.addTask(t4);

        tms.changeStatus(t1, TaskStatus.COMPLETED);

        tms.assignTask(t1,u2);

        tms.listTasks(u2);
        tms.listTasks(u1);

        tms.filterTasks(TaskStatus.NOT_STARTED, TaskPriority.HIGH, u1);
    }
}
