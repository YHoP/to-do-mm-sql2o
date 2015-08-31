import java.util.HashMap;
import java.util.List;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      // model.put("template", "templates/tasks.vtl");
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/tasks", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Task> tasks = Task.all();
      model.put("tasks", tasks);
      model.put("template", "templates/tasks.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/tasks/:id", (request,response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Task task = Task.find(id);
      model.put("task", task);
      model.put("allCategories", Category.all());
      model.put("template", "templates/task.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/tasks", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String description = request.queryParams("description");
      Task newTask = new Task(description);
      newTask.save();
      response.redirect("/tasks");
      return null;
    });

    put("/tasks/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Task task = Task.find(Integer.parseInt(request.params("id")));
      String description = request.queryParams("description");
      task.update("description");
      model.put("template", "templates/task.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    delete("/tasks/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Task task = Task.find(Integer.parseInt(request.params("id")));
      task.delete();
      model.put("template", "templates/task.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/categories", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Category> categories = Category.all();
      model.put("categories", categories);
      model.put("template", "templates/categories.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/categories/:id", (request,response) ->{
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Category category = Category.find(id);
      model.put("category", category);
      model.put("allTasks", Task.all());
      model.put("template", "templates/category.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/categories", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      Category newCategory = new Category(name);
      newCategory.save();
      response.redirect("/categories");
      return null;
    });

    post("/add_tasks", (request, response) -> {
      int taskId = Integer.parseInt(request.queryParams("task_id"));
      int categoryId = Integer.parseInt(request.queryParams("category_id"));
      Category category = Category.find(categoryId);
      Task task = Task.find(taskId);
      category.addTask(task);
      response.redirect("/categories/" + categoryId);
      return null;
    });

    get("/:category_id/delete_tasks/:task_id", (request, response) -> {
      int category_id = Integer.parseInt(request.params("category_id"));
      int task_id = Integer.parseInt(request.params("task_id"));
      Task myTask = Task.find(task_id);
      myTask.delete();
      response.redirect("/categories/" + category_id);
      return null;
    });


    post("/delete_tasks", (request, response) -> {
      int taskId = Integer.parseInt(request.queryParams("task_id"));
      Task myTask = Task.find(taskId);
      myTask.delete();
      response.redirect("/tasks");
      return null;
    });

    post("/add_categories", (request, response) -> {
      int taskId = Integer.parseInt(request.queryParams("task_id"));
      int categoryId = Integer.parseInt(request.queryParams("category_id"));
      Category category = Category.find(categoryId);
      Task task = Task.find(taskId);
      task.addCategory(category);
      response.redirect("/tasks/" + taskId);
      return null;
    });

    post("/delete_categories", (request, response) -> {
      int categoryId = Integer.parseInt(request.queryParams("category_id"));
      Category category = Category.find(categoryId);
      category.delete();
      response.redirect("/categories");
      return null;
    });


    get("/categories/:id/update", (request,response) ->{
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Category category = Category.find(id);
      model.put("category", category);
      model.put("template", "templates/category-update.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    post("/update_categories/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      int category_id = Integer.parseInt(request.params("id")); ///?category_id
      Category myCategory = Category.find(category_id);
      String name = request.queryParams("name");
      myCategory.update(name);
      response.redirect("/categories/" + category_id);
      return null;
    });

    get("/tasks/:id/update", (request,response) ->{
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Task task = Task.find(id);
      model.put("task", task);
      model.put("template", "templates/task-update.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/update_tasks/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int task_id = Integer.parseInt(request.params("id"));
      Task myTask = Task.find(task_id);
      String description = request.queryParams("description");
      myTask.update(description);
      response.redirect("/tasks/" + task_id);
      return null;
    });

    post("/complete_tasks/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int task_id = Integer.parseInt(request.params("id"));
      Task myTask = Task.find(task_id);
      myTask.complete();
      response.redirect("/tasks");
      return null;
    });

  }
}
