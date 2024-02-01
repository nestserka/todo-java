<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ToDo List</title>
    <link href="/css/styles.css" rel="stylesheet">
</head>
<head>
<body>
<main>
<div id="header">
   <h1>ToDo List</h1>
</div>

<div id="content">

    <fieldset>
        <legend>Add Car</legend>
        <form name="car" action="add" method="post">
            Make : <input type="text" name="make" />	<br/>
            Model: <input type="text" name="model" />	<br/>
            <input type="submit" value="   Save   " />
        </form>
    </fieldset>

    <br/>
    <table class="datatable">
        <tr>
            <th>Make</th>  <th>Model</th>
        </tr>
        <#list model["tasks"] as car>
            <tr>
            <tr>
                <td>${car.description}</td> <td>${car.status}</td>
            </tr>
            </tr>
        </#list>
    </table>
</div>
</main>
</body>
</html>