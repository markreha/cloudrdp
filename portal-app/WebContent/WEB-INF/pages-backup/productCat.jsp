<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

   <div><h2 style="margin: 0.5em 0">Select an Image</h2></div>
  
    <div class="input row">
      <i style="color: #522398"class="fas fa-search icon"></i><input placeholder="Search" type="text" id="filter" autocomplete="off" onkeyup="filterFunction()"/> 
    </div>

    <div class="images-container">
      
      <div id="myBtn" class="column images">
      <img src="https://cdn-images-1.medium.com/max/960/1*ZGEUEy_SifxtHG-CSAWsZA.png"/>
        <span style="margin-top: 10px">Java 8 Tomcat 8.5 For Baby Jesus</span>
      </div>
      
      <div id="myBtn" class="column images">
      <img src="https://cdn-images-1.medium.com/max/960/1*ZGEUEy_SifxtHG-CSAWsZA.png"/>
        <span style="margin-top: 10px">Java 8 Tomcat 8.5 For Baby Jesus</span>
      </div>

    </div>
    
          
      <!-- The Modal -->
<div id="myModal" class="modal">

  <!-- Modal content -->
<!--   <div class="modal-content">
    <div class="modal-header">
      <span class="close">&times;</span>
      <h2>Modal Header</h2>
    </div>
    <div class="modal-body">
      <p>Some text in the Modal Body</p>
      <p>Some other text...</p>
    </div>
    <div class="modal-footer">
      <h3>Modal Footer</h3>
    </div>
  </div> -->
  
  <div class="modal-content">
  <div class="modal-header">
      <span class="close">&times;</span>
	  <h2 class="mgn-10">Container Details</h2>
    </div>
	<div class="form">
	  
	  <div class="column">
	   <div class="row">
	   	<div class="pad-20" style="width:150px">Name:</div>
	    <input minlength="5" type="text" class="input" required="true" value="" maxlength="20" />
	   </div>
	  </div>
	  
	  <div class="column">
	   <div class="row">
	   	<div class="pad-20" style="width:150px">Storage:</div>
	    <input minlength="5" type="text" class="input" required="true" value="" maxlength="20" />
	   </div>
	  </div>
	  
	  <input class="btn-purple font-bold" type="submit" value="Create Container" />
	  
	</div>
	
	</div>
</div>
      