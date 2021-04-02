// 구독자 정보 보기
document.querySelector("#subscribeBtn").onclick = (e) => {
	e.preventDefault();
	document.querySelector(".modal-follow").style.display = "flex";

	let userId = $("#userId").val();
	console.log(userId);

	// ajax 통신후에 json 리턴 -> javascript 오브젝트변경 => for문 돌면서 뿌리기
	$.ajax({
		url: `/user/${userId}/follow`,
	}).done((res) => {
		$("#follow_list").empty();
		res.data.forEach((u) => {
			let item = makeSubscribeInfo(u);
			$("#follow_list").append(item);
		});
	}).fail((error) => {
		alert("오류 : " + error);
	});
};



function makeSubscribeInfo(u) {
	let item = `<div class="follower__item" id="follow-${u.userId}">`;
	item += `<div class="follower__img">`;
	item += `<img src="/upload/${u.profileImageUrl}" alt=""  onerror="this.src='/images/person.jpg'"/>`;
	item += `</div>`;
	item += `<div class="follower__text">`;
	item += `<h2>${u.username}</h2>`;
	item += `</div>`;
	item += `<div class="follower__btn">`;
	if (!u.equalState) {
		if (u.followState) {
			item += `<button class="cta blue" onclick="followOrUnFollowModal(${u.userId})">구독취소</button>`;
		} else {
			item += `<button class="cta" onClick="followOrUnFollowModal(${u.userId})">구독하기</button>`;
		}


	}

	item += `</div>`;
	item += `</div>`;

	return item;
}

function followOrUnFollowModal(userId){
	let text = $(`#follow-${userId} button`).text();

	if(text === "구독취소"){
		$.ajax({
			type: "DELETE",
			url: "/follow/"+userId,
			dataType: "json"
		}).done(res=>{
			$(`#follow-${userId} button`).text("구독하기");
			$(`#follow-${userId} button`).toggleClass("blue");
		});
	}else{
		$.ajax({
			type: "POST",
			url: "/follow/"+userId,
			dataType: "json"
		}).done(res=>{
			$(`#follow-${userId} button`).text("구독취소");
			$(`#follow-${userId} button`).addClass("blue");
			$(`#follow-${userId} button`).toggleClass("blue");
		});
	}
}


function followOrUnFollowProfile(userId){
	let text = $(`#follow_profile_btn`).text();

	if(text === "구독취소"){
		$.ajax({
			type: "DELETE",
			url: "/follow/"+userId,
			dataType: "json"
		}).done(res=>{
			$(`#follow_profile_btn`).text("구독하기");
			$(`#follow_profile_btn`).toggleClass("blue");
		});
	}else{
		$.ajax({
			type: "POST",
			url: "/follow/"+userId,
			dataType: "json"
		}).done(res=>{
			$(`#follow_profile_btn`).text("구독취소");
			$(`#follow_profile_btn`).toggleClass("blue");
		});
	}
}



    function update(userId){ //회원정보 수정
    	
    	event.preventDefault();
    	
    	let data = $("#profile_setting").serialize();	  
    	//form 태그의 name 값을 그대로 받아서 키 값 형태로 전달받음, let은 타입을 자동으로 정하니 아마도 String? 그냥 거의 다 String으로 받는다고 생각하면 됨. int도 아마 number로 캐스팅해줘야 될 듯.
    	console.log(data);  
    	
    	
    	$.ajax({
    		type:"PUT",
			url:"/user/"+userId,
			data: data,
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			dataType:"JSON"	
    	}).done(res=>{
    		alert("수정 성공");
    		location.href = "/user/" + userId
    	});
    	
    }


/*$("#follow-1 button").text("구독하기");
$("#follow-1 button").클래스명변경("cta");*/

function closeFollow() {
	document.querySelector(".modal-follow").style.display = "none";
}
document.querySelector(".modal-follow").addEventListener("click", (e) => {
	if (e.target.tagName !== "BUTTON") {
		document.querySelector(".modal-follow").style.display = "none";
	}
});
function popup(obj) {
	console.log(obj);
	document.querySelector(obj).style.display = "flex";
}
function closePopup(obj) {
	console.log(2);
	document.querySelector(obj).style.display = "none";
}
document.querySelector(".modal-info").addEventListener("click", (e) => {
	if (e.target.tagName === "DIV") {
		document.querySelector(".modal-info").style.display = "none";
	}
});
document.querySelector(".modal-image").addEventListener("click", (e) => {
	if (e.target.tagName === "DIV") {
		document.querySelector(".modal-image").style.display = "none";
	}
});


function clickFollow(e) {

	let _btn = e;
	console.log(_btn.textContent);
	if (_btn.textContent === "구독취소") {
		_btn.textContent = "구독하기";
		_btn.style.backgroundColor = "#fff";
		_btn.style.color = "#000";
		_btn.style.border = "1px solid #ddd";
	} else {
		_btn.textContent = "구독취소";
		_btn.style.backgroundColor = "#0095f6";
		_btn.style.color = "#fff";
		_btn.style.border = "0";
	}
}