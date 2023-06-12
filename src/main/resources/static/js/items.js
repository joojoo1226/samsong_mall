function search(){
    let search = existId("search").value;
}

function quanCount(){
	let quanVal = document.querySelector(".quanInput");
	let quantity = existId("quantity").value;
	// 입력된 값이 빈칸 이라면
	if(isNaN(parseInt(quanVal.value))){
		alert("값은 하나이상 입력하셔야 합니다.")
		quanVal.value = parseInt(1);
		return;
	}
	// 입력된 값이 0이하라면 1로 초기화
	if(parseInt(quanVal.value) < 1){
		alert("값은 하나이상 입력하셔야 합니다.")
		quanVal.value = parseInt(1);
	}
	// 입력된 값이 재고 이상이라면 재고수량으로 초기화
	if(quanVal.value > quantity){
		alert("재고 이상으로 선택할수 없습니다.")
		quanVal.value = parseInt(quantity);
		return;
	}

	quanVal.value = quanVal.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');
}

function quanPlus(current) {
  let quanVal = document.querySelector(".quanInput");
  let quantity = existId("quantity").value;

  // 값이 하나 이상 없을 시 0으로 초기화
  if (isNaN(parseInt(quanVal.value))) {
    quanVal.value = parseInt(0);
  }

  // 입력된 값 더함
  let newValue = parseInt(quanVal.value) + parseInt(current);
  // 입력된 값이 0이하라면 1로 초기화
  if (newValue < 1) {
    quanVal.value = parseInt(1);
  }
  // 입력된 값이 재고 이상이라면 재고수량으로 초기화
  else if (newValue > quantity) {
    quanVal.value = parseInt(quantity);
  }
  else {
    quanVal.value = newValue;
  }
}
function cart() {
    let amount = existId("quanInput").value;
    let url = window.location.pathname;
    let lastIdx = url.lastIndexOf('/');
    let prd_idx = url.substring(lastIdx + 1);
    let data = {};
    data.crt_amount = amount;
    data.prd_idx = prd_idx;
    data.type = "cart"
    httpUtil.defaultRequest('/member/cartHeart/add','post', data, (data) => {
        alert(data.data.message);
    })
}


function heart() {
    let amount = existId("quanInput").value;
    let url = window.location.pathname;
    let lastIdx = url.lastIndexOf('/');
    let prd_idx = url.substring(lastIdx + 1);
    let data = {};
    data.prd_idx = prd_idx;
    data.type = "heart"
    httpUtil.defaultRequest('/member/cartHeart/add','post', data, (data) => {
        alert(data.data.message);
    })
}

function manage(){
    let url = window.location.pathname;
    let lastIdx = url.lastIndexOf('/');
    let prd_idx = url.substring(lastIdx + 1);
    location.href = "/basic/items/manage/"+prd_idx
}








