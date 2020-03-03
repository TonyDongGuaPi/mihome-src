package miuipub.util.async.tasks.listeners;

import miuipub.util.async.Task;
import miuipub.util.async.TaskManager;

public abstract class BaseTaskListener implements Task.Listener {
    public Object a(TaskManager taskManager, Task<?> task, Object obj) {
        return obj;
    }

    public void a(TaskManager taskManager, Task<?> task) {
    }

    public void a(TaskManager taskManager, Task<?> task, int i, int i2) {
    }

    public void a(TaskManager taskManager, Task<?> task, Exception exc) {
    }

    public void b(TaskManager taskManager, Task<?> task) {
    }

    public void c(TaskManager taskManager, Task<?> task) {
    }
}
