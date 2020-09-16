"use strict";

document.addEventListener("DOMContentLoaded", function () {
  fetch("http://127.0.0.1:8080/TapnShop/stores", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": true
    }
  }).then(function (response) {
    return response.json();
  }).then(function (data) {
    console.log("data is ", data);
    data.forEach(function (element) {
      var section = document.querySelector(".stores");
      var myStores = "<div class=\"mystore\">\n\t\t\t\t\t<img class=\"images\" src=\"img/".concat(element.storeImage, "\" alt=\"\">\n\t\t\t\t\t<h3>").concat(element.storeName, "</h3>\n\t\t\t\t\t<button onclick=\"getFoodItems(this);sessionStorage.setItem('id',").concat(element.id, ")\" value=\"").concat(element.id, "\" type=\"submit\" >Buy Now</button>\n\t\t\t\t\t<button class=\"cancelbtn\" onclick=\"deleteStore(this)\" value=\"").concat(element.id, "\" type=\"submit\" >Remove Store</button>\n\n\t\t\t\t\t\n\t\t\t\t\t</div>\n\t\t\t\t\t");
      section.insertAdjacentHTML("beforeend", myStores);
    });
  })["catch"](function (error) {
    console.log(error);
  });
}); // BUY NOW section

var getFoodItems = function getFoodItems(obj) {
  var sectionFoodstuff = document.querySelector(".foodDisplay");
  sectionFoodstuff.innerHTML = "";
  sectionFoodstuff.innerHTML = "<button type=\"button\" class=\"headButton\" onclick=\"document.getElementById('displayFoods').style.display='block'\">Add FoodItems</button>\n\t  <section class=\"stores\"> </section>";
  var store = obj.getAttribute("value");
  fetch("http://127.0.0.1:8080/TapnShop/BuyNow?store=" + store).then(function (response) {
    return response.json();
  }).then(function (data) {
    console.log(data);
    data.forEach(function (element) {
      var section = document.querySelector(".stores");
      var myFood = "<div class=\"mystore\">\n\t\t\t\t\n\t\t\t\t\t<img class=\"images\" src=\"img/".concat(element.foodImage, "\" alt=\"\">\n\t\t\t\t\t<h3>").concat(element.foodName, "</h3>\n\t\t\t\t\t<h3>").concat(element.foodType, "</h3>\n\t\t\t\t\t<h3>").concat(element.price, "ksh</h3>\n\t\t\t\t\t<button type=\"submit\" >Add to Cart</button>\n\t\t\t\t\t</div>\n\t\t\t\t\t");
      section.insertAdjacentHTML("beforeend", myFood);
    });
  });
}; // ADD A STORE
// Get the modal


var modal = document.getElementById("headButton"); // When the user clicks anywhere outside of the modal, close it

window.onclick = function (event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}; //Delete Store


var deleteStore = function deleteStore(obj) {
  var store = obj.getAttribute("value");
  fetch("http://127.0.0.1:8080/TapnShop/stores?store=" + store, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": true
    }
  });
  window.location.reload();
};