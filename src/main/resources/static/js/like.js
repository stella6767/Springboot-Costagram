/*function like() { //좋아요	

	let _buttonI = event.target;

	_buttonI.classList.add("fas");
	_buttonI.classList.add("active");
	_buttonI.classList.remove("far");
	_buttonI.removeAttribute("onclick", "unLike()");

}

function unLike() { //좋아요 해제

	let _buttonI = event.target;
	_buttonI.classList.remove("fas");
	_buttonI.classList.remove("active");
	_buttonI.classList.add("far");

}*/


function likeOrUnLike(imageId) {
  let _buttonI = event.target;
  if (_buttonI.classList.contains("far")) {
	  $.ajax({
		  type: "POST",
		  url: `/image/${imageId}/likes`,
		  dataType: "json"
	  }).done(res=>{
		    let likeCountStr  = $(`#like_count_${imageId}`).text();
		    let likeCount = Number(likeCountStr) + 1;
		    $(`#like_count_${imageId}`).text(likeCount);
		    
		    _buttonI.classList.add("fas");
		    _buttonI.classList.add("active");
		    _buttonI.classList.remove("far");
	  });
	  
	  

  } else {
	  $.ajax({
		  type: "DELETE",
		  url: `/image/${imageId}/likes`,
		  dataType: "json"
	  }).done(res=>{
		    let likeCountStr  = $(`#like_count_${imageId}`).text();
		    let likeCount = Number(likeCountStr) - 1;
		    $(`#like_count_${imageId}`).text(likeCount);
		    
		    _buttonI.classList.remove("fas");
		    _buttonI.classList.remove("active");
		    _buttonI.classList.add("far");
	  });  

  }
}

