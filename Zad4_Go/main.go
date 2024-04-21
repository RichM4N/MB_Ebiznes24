package main

import (
	"fmt"
	"net/http"
	"strconv"

	"github.com/labstack/echo/v4"
)

type Product struct {
	name  string
	price string
}

var ProductList = []Product{
	Product{name: "samsung", price: "1000"},
	Product{name: "iphone", price: "2000"},
}

func createProduct(c echo.Context) error {
	name_ := c.QueryParam("name")
	price_ := c.QueryParam("price")

	newProduct := Product{name: name_, price: price_}
	ProductList = append(ProductList, newProduct)

	return c.String(http.StatusOK, "")
}

func deleteProduct(c echo.Context) error {
	id_, _ := strconv.Atoi(c.QueryParam("id"))
	ProductList = append(ProductList[:id_], ProductList[id_+1:]...)
	return c.String(http.StatusOK, "")
}

func updateProduct(c echo.Context) error {
	id_, _ := strconv.Atoi(c.QueryParam("id"))
	name_ := c.QueryParam("name")
	price_ := c.QueryParam("price")
	ProductList[id_].name = name_
	ProductList[id_].price = price_
	return c.String(http.StatusOK, "")
}

func getProducts(c echo.Context) error {
	responseText := ""
	for i, _ := range ProductList {
		responseText += "Name: " + ProductList[i].name + " Price: " + ProductList[i].price + "\n"
	}
	return c.String(http.StatusOK, responseText)
}

func main() {
	e := echo.New()

	e.POST("/createProduct", createProduct)
	e.GET("/getProducts", getProducts)
	e.PUT("/updateProduct", updateProduct)
	e.DELETE("/deleteProduct", deleteProduct)
	e.Start(":3000")
	fmt.Println("Hello, world!")
}
