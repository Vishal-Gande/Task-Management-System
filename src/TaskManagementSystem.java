import java.util.HashMap;

public class TaskManagementSystem {

    public static TaskManagementSystem instance ;

    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, User> users;

    public TaskManagementSystem() {
        tasks = new HashMap<>();
        users = new HashMap<>();
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
            users.remove(task.getUserId());
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
        User olduser = users.get(task.getUserId());
        olduser.removeTask(task);

        task.setUser(newuser.getId());
        newuser.addTask(task);

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
