document.addEventListener("DOMContentLoaded", function(event) {
	console.log("DOM fully loaded and parsed");
	
	//Get the modal
	let modal = document.getElementById('myModal');
	
	// Get the button that opens the modal
	let btn = document.getElementById("myBtn");
	
	// Get the <span> element that closes the modal
	let span = document.getElementsByClassName("close")[0];
	
	// When the user clicks the button, open the modal 
	btn.onclick = function() {
	  modal.style.display = "block";
	}
	
	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
	  modal.style.display = "none";
	}
	
	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
	  if (event.target == modal) {
	    modal.style.display = "none";
	  }
	}
});


function headerMenu() {
  let navbar = document.getElementById("myTopnav");
  if (navbar.className === "topnav") {
    navbar.className += " responsive";
  } else {
    navbar.className = "topnav";
  }
}

function filterFunction() {
  let filter = document.querySelector("#filter");
  
  let itemArray = document.getElementsByClassName("item");
  
  for (let i=0; i < itemArray.length; i++)
    {
      let data = itemArray[i].querySelector("#metadata").getAttribute("data-meta");
      let dataArray = data.split(" ");
      
      for (let j=0; j < dataArray.length; j++)
        {
          if(dataArray[j].toUpperCase().indexOf(itemArray[i] > -1))
             {
                itemArray[i].style.display = "";
             } else {
               itemArray[i].style.display = "none";
             }
        }
      console.log(data);
    }
  
  console.log("hello " + filter.value.toUpperCase());
  console.log(data);
}