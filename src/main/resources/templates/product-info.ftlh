<!DOCTYPE html>
<html>
<head>
    <title>MarketPlace</title>
</head>
<body>
<h1>MarketPlace Arz</h1>
<hr>
<h4>Информация о товаре</h4>

<#list images as img>
    <br> <img src="/images/${img.id}" height="120px">
</#list>

<br> <b>Название товара: </b>${product.title}
<br> <b>Описание товара: </b>${product.description}
<br> <b>Цена товара: </b>${product.price}
<br> <b>Город продажи товара: </b>${product.city}
<br> <b>Продавец товара: </b>${product.author}

<hr>

<form action="/product/delete/${product.id}" method="post">
    <input type="submit" value="Удалить товар">
</form>


</body>
</html>