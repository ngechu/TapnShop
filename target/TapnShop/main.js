
document.addEventListener("DOMContentLoaded",()=>{

	fetch("http://127.0.0.1:8080/TapnShop/stores", {
		method: "GET",
		headers: {
			"Content-Type": "application/json",
			"Access-Control-Allow-Origin": true,
		},
	})
		.then((response) => response.json())
		.then((data) => {
			data.forEach((element) => {
				const section = document.querySelector(".stores");

				const myStores = `<div class="mystore">
					<img class="images" src="img/${element.storeImage}" alt="">
					<h3>${element.storeName}</h3>
					<button onclick="getFoodItems(this)" value="${element.id}">Buy Now</button>
					`;

				section.insertAdjacentHTML("beforeend", myStores);
				
			});
		})
		.catch((error) => {
			console.log("There is an error");
		});
});

let getFoodItems = function(ob){
	let store = ob.getAttribute("value");
	// fetch("http://127.0.0.1:8080/TapnShop/HealthyDrinks?store="+store, {
	// 	method: "GET",
	// 	headers: {
	// 		"Content-Type": "application/json",
	// 		"Access-Control-Allow-Origin": true,
	// 	},
	// })
	// .then((response) => response.json())
	// .then((data) => {
	// 	console.log(data);
	// })
	// .catch((error) => {
	// 	console.log("There is an error"+error);
	// });

	fetch("http://127.0.0.1:8080/TapnShop/HealthyDrinks?store=" + store)
		.then((response) => response.json())
		.then((data) => console.log(data))
		.catch((error) => {
			console.log("There is an error" + error);
		});
}



 


