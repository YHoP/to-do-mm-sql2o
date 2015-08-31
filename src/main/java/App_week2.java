/*
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;
import java.util.List;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

  get("/", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    model.put("tasks", request.session().attribute("tasks"));

    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  // get("/categories", (request, response) -> {
  //   HashMap<String, Object> model = new HashMap<String, Object>();
  //   model.put("categories", Category.all());
  //   model.put("template", "templates/categories.vtl");
  //   return new ModelAndView(model, layout);
  // }, new VelocityTemplateEngine());
  //
  // get("categories/new", (request, response) -> {
  //   HashMap<String, Object> model = new HashMap<String, Object>();
  //   model.put("template", "templates/category-form.vtl");
  //   return new ModelAndView(model, layout);
  // }, new VelocityTemplateEngine());

  post("/categories", (request, response) ->{
    HashMap<String, Object> model = new HashMap<String, Object>();
    String name = request.queryParams("name");
    Category newCategory = new Category(name);
    newCategory.save();

    model.put("categories", Category.all());
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/categories/:id", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();

    Category category = Category.find(Integer.parseInt(request.params(":id")));
    model.put("category", category);

    model.put("template", "templates/category.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  // get("categories/:id/tasks/new", (request, response) -> {
  //   HashMap<String, Object> model = new HashMap<String, Object>();
  //   Category category = Category.find(Integer.parseInt(request.params(":id")));
  //   List<Task> tasks = category.getTasks();
  //   model.put("category", category);
  //   model.put("tasks", tasks);
  //   model.put("template", "templates/category-tasks-form.vtl");
  //   return new ModelAndView(model, layout);
  // }, new VelocityTemplateEngine());

  post("/tasks", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();

    int categoryId = Integer.parseInt(request.queryParams("categoryId"));
    Category category = Category.find(categoryId);
    // List<Task> tasks = category.getTasks();

    String description = request.queryParams("description");
    Task newTask = new Task(description, categoryId);
    newTask.save();
    // tasks.add(newTask);

    model.put("category", category);
    model.put("categories", Category.all());
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("categories/:id/update", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Category category = Category.find(Integer.parseInt(request.params(":id")));

    model.put("category", category);
    model.put("template", "templates/update-form.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  post("categories/:id/update", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();
    Category category = Category.find(Integer.parseInt(request.params(":id")));
    String name = request.queryParams("name");
    category.update(name);

    response.redirect("/");
    return null;
  });

  // post("/tasks/:id", (request, response) -> {
  //   HashMap<String, Object> model = new HashMap<String, Object>();
  //   Task task = Task.find(Integer.parseInt(request.params("id")));
  //   Category category = Category.find(task.getCategoryId());
  //   String description = request.queryParams("description");
  //   task.update("description");
  //   model.put("template", "templates/task.vtl");
  //   return new ModelAndView(model, layout);
  // }, new VelocityTemplateEngine());
  //
  // post("/tasks/:id/delete", (request, response) -> {
  //   HashMap<String, Object> model = new HashMap<String, Object>();
  //   Task task = Task.find(Integer.parseInt(request.params("id")));
  //   task.delete();
  //   model.put("template", "templates/task.vtl");
  //   return new ModelAndView(model, layout);
  // }, new VelocityTemplateEngine());

 }
}
*/
