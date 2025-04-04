import java.util.ArrayList;
import java.util.Date;

public class Task {

    private int id;
    private String name;
    private String description;
    private TaskStatus status;
    // add reminder
    private int userId;
    private Date date;
    private int createdBy;
    private TaskPriority priority;
    private ArrayList<Observer> observers;

    public Task(int id, String name, String description, TaskStatus status, int userId, Date date, int createdBy, TaskPriority priority, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.userId = userId;
        this.date = date;
        this.createdBy = createdBy;
        this.priority = priority;
        this.observers = new ArrayList<>();
        this.addObserver(user);
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.sendNotification("Notifying observer..");
        }
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public TaskStatus getStatus() {
        return status;
    }
    public int getUserId() {
        return userId;
    }
    public Date getDate() {
        return date;
    }
    public int getCreatedBy() {
        return createdBy;
    }
    public TaskPriority getPriority() {
        return priority;
    }

    public void setStatus(TaskStatus newStatus)
    {
        this.status = newStatus;
    }

    public void setUser(int newUserId) {
        this.userId = newUserId;
    }


}
