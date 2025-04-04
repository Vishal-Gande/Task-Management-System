import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TaskManagementSystem {

    private static TaskManagementSystem instance ;

    private ConcurrentHashMap<Integer, Task> tasks;
    private ConcurrentHashMap<Integer, User> users;

    public TaskManagementSystem() {
        tasks = new ConcurrentHashMap<>();
        users = new ConcurrentHashMap<>();
    }

    public static TaskManagementSystem getInstance() {
        if (instance == null) {
            synchronized (TaskManagementSystem.class) {
                if (instance == null) {
                    instance = new TaskManagementSystem();
                }
            }
        }
        return instance;
    }

    public void addTask(Task task) {
        // validate if ID already exists
        if(tasks.containsKey(task.getId())) {
            System.out.println("Task already exists");
        }
        else
        {
            tasks.put(task.getId(), task);
            users.get(task.getUserId()).addTask(task);
            System.out.println("task: " + task.getName() + " with ID = " + task.getId() + " added");
        }
    }

    public void removeTask(Task task) {

        if(tasks.containsKey(task.getId())) {
            tasks.remove(task.getId());
            //users.remove(task.getUserId());
            users.get(task.getUserId()).removeTask(task);
            System.out.println("task: " + task.getName() + "with ID = " + task.getId() + " removed");
        }
        else
        {
            System.out.println("task: " + task.getName() + "with ID = " + task.getId() + " not found");
        }
    }

    public void changeStatus(Task task, TaskStatus newStatus) {
        if(tasks.containsKey(task.getId())) {
            task.setStatus(newStatus);
        }
        else System.out.println("task: " + task.getName() + "with ID = " + task.getId() + " not found");

    }

    public void addUser(User user){
        this.users.put(user.getId(),user);
        System.out.println("User with ID" + user.getId()+ "added");
    }

    public void removeUser(User user){
        if(users.containsKey(user.getId())) {
            this.users.remove(user.getId());
        }
        else System.out.println("User with ID" + user.getId()+ "not found");

    }

    public void listTasks(User user){
        user.listTasks();

    }

    public void assignTask(Task task, User newuser)
    {
        task.removeObserver(users.get(task.getUserId()));
        User olduser = users.get(task.getUserId());
        olduser.removeTask(task);
        olduser.sendNotification(olduser.getName() + "has been removed from task:" + task.getName());

        task.setUser(newuser.getId());
        newuser.addTask(task);
        task.addObserver(users.get(task.getUserId()));
        task.notifyObservers();
        //newuser.sendNotification(newuser.getName() + "has been assigned to task: " + task.getName());
    }

    public void filterTasks(TaskStatus status, TaskPriority priority, User user)
    {
        System.out.println("Filtering tasks...");
        for(Task task : tasks.values()) {
            if(task.getStatus().equals(status) && task.getPriority().equals(priority) && task.getUserId()==user.getId()) {
                System.out.println("- Task " + task.getName() + " with ID = " + task.getId());
            }
        }
    }

}
