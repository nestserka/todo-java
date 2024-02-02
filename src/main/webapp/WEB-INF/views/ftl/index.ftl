<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ToDo List</title>
    <link href="/css/styles.css" rel="stylesheet">
    <script src="/js/task.js" defer></script>
</head>
<head>
<body>
    <header>
        <h1>Task List</h1>
    </header>
    <main>
          <section id="section-current" class="section">
          <h2>Task List</h2>
          <h3>In Progress </h3>
        <#list model["tasks"] as task>
        <#if task.status == 0>
          <ul>
            <li class="task-item">
            <div class="task-input" contenteditable="false">${task.description}</div>
            <button class="button btn-edit" id="editButton-${task.id}" onclick="editTask(${task.id}, '${task.description}', ${task.status})">Edit</button>
              <button class="button btn-delete" onclick="deleteTask(${task.id})">
                <img class="delete-image" src="./delete.png" alt="Deleta task image">
              </button>
            </li>
          </ul>
          <#elseif task.status == 1>
          <h3>Done</h3>
          <ul>
            <li class="task-item">
            <div class="task-input" contenteditable="false">${task.description}</div>
            <button class="button btn-edit" id="editButton-${task.id}" onclick="editTask(${task.id}, '${task.description}', ${task.status})">Edit</button>
              <button class="button btn-delete" onclick="deleteTask(${task.id})">
                <img class="delete-image" src="./delete.png" alt="Deleta task image">
              </button>
            </li>
          </ul>
          <h3>Paused </h3>
          <#elseif task.status == 2>
          <ul>
            <li class="task-item">
            <div class="task-input" contenteditable="false">${task.description}</div>
            <button class="button btn-edit" id="editButton-${task.id}" onclick="editTask(${task.id}, '${task.description}', ${task.status})">Edit</button>
              <button class="button btn-delete" onclick="deleteTask(${task.id})">
                <img class="delete-image" src="./delete.png" alt="Deleta task image">
              </button>
            </li>
          </ul>
      </#if>
    </#list>
        </section>
        <section id="section-new-task" class="section">
            <h2>New Task</h2>
            <form name="info" action="add" method="post" class="info__wrapper">
                <div class="info__wrapper-input"> 
                <label for="description"> Name</label>
                <input type="text" name="description"/>
                </div>
                <div class="info__wrapper-input"> 
                <label for="status">Status</label>
                <select name="status">
                  <option value="0">In Progress</option>
                  <option value="1">Done</option>
                  <option value="2">Paused</option>
                </select>
                </div>
                <input type="submit" value="Save" class="btn-submit"/>
              </form>
              </section>
    </main>
    <div id="modal" class="modal"></div>
</body>
</html>