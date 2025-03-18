<!DOCTYPE html>
<html>
<head>
    <title>MarketPlace</title>
</head>
<body>
<h1>MarketPlace Arz</h1>
<hr>
<h4>Товары со всей России</h4>

<form action="/" method="get">
    <label>
        Поиск:
        <input type="text" name="title" placeholder="Введите название товара"/>
    </label><br>

    <input type="submit" value="Поиск">
</form>

<#list products as product>

    <div>
        <p>
            <b>${product.title}</b> |
            <span>${product.price} руб.</span>
        </p>

        <a href="/product/${product.id}">Подробнее</a>
    </div>

    <#else>
    <h3>Список товаров пуст</h3>

</#list>

<hr>

<h3>Создать новый товар</h3>
<form action="/product/create" method="post">
    <label>
        Название товара:
        <input type="text" name="title"/>
    </label><br>

    <label>
        Описание товара:
        <input type="text" name="description"/>
    </label><br>

    <label>
        Цена:
        <input type="number" name="price"/>
    </label><br>

    <label>
        Город объявления:
        <input type="text" name="city"/>
    </label><br>

    <label>
        Имя продавца:
        <input type="text" name="author"/>
    </label><br>

    <input type="submit" value="Сохранить товар">

</form>


</body>
</html>