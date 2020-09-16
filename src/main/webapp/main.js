document.addEventListener("DOMContentLoaded", () => {
	fetch("http://127.0.0.1:8080/TapnShop/stores", {
		method: "GET",
		headers: {
			"Content-Type": "application/json",
			"Access-Control-Allow-Origin": true,
		},
	})
		.then((response) => response.json())
		.then((data) => {
			console.log("data is ",  data);
			data.forEach((element) => {
				const section = document.querySelector(".stores");

				const myStores = `<div class="mystore">
					<img class="images" src="img/${element.storeImage}" alt="">
					<h3>${element.storeName}</h3>
					<button onclick="getFoodItems(this);sessionStorage.setItem('id',${element.id})" value="${element.id}" type="submit" >Buy Now</button>
					<button class="cancelbtn" onclick="deleteStore(this)" value="${element.id}" type="submit" >Remove Store</button>

					
					</div>
					`;

				section.insertAdjacentHTML("beforeend", myStores);
			});
		})
		.catch((error) => {
			console.log(error);
		});
});

// BUY NOW section

let getFoodItems = function (obj) {

	 let sectionFoodstuff = document.querySelector(".foodDisplay");

	 sectionFoodstuff.innerHTML = "";

	 sectionFoodstuff.innerHTML = `<button type="button" class="headButton" onclick="document.getElementById('displayFoods').style.display='block'">Add FoodItems</button>
	  <section class="stores"> </section>`;

	  
	 
	let store = obj.getAttribute("value");

	fetch("http://127.0.0.1:8080/TapnShop/BuyNow?store=" + store)
		.then((response) => response.json())
		.then((data) => {
			console.log(data);

			data.forEach((element) => {
				const section = document.querySelector(".stores");

				const myFood = `<div class="mystore">
				
					<img class="images" src="img/${element.foodImage}" alt="">
					<h3>${element.foodName}</h3>
					<h3>${element.foodType}</h3>
					<h3>${element.price}ksh</h3>
					<button type="submit" >Add to Cart</button>
					</div>
					`;

				section.insertAdjacentHTML("beforeend", myFood);
			});
		});
}

// ADD A STORE

// Get the modal

	var modal = document.getElementById("headButton");

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function (event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
	};



//Delete Store
let deleteStore=function(obj){
	let store = obj.getAttribute("value");

	fetch("http://127.0.0.1:8080/TapnShop/stores?store=" + store, {
		method: "DELETE",
		headers: {
			"Content-Type": "application/json",
			"Access-Control-Allow-Origin": true,
		},
	});
		
	window.location.reload();

}		
