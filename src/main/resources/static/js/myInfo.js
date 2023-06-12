function getMyCart() {
    httpUtil.defaultRequest('/member/cart/get','get', null, (data) => {
                let html = "";
                for(let i of data.data) {
                    html += `
                        <tr>
                            <td><input type="checkbox"></td>
                            <td><img src='/api/img/${i.file_id}'></td>
                            <td>${i.prd_name}</td>
                            <td>${i.crt_amount}</td>
                            <td>${i.total_price} 원</td>
                            <td>
                                <button class="view" onclick="goProduct(${i.prd_idx})">view</button>
                                <button class="delete" onclick="deleteCart(${i.crt_idx})">Delete</button>
                            </td>
                        </tr>
                    `
                }
    let cartBody = document.getElementById("cart_body");
    cartBody.innerHTML = html;
    })

}

function getMyHeart(){
    httpUtil.defaultRequest('/member/heart/get','get', null, (data) => {
                let html = "";
                for(let i of data.data) {
                    html += `
                        <tr>
                            <td><img src='/api/img/${i.file_id}'></td>
                            <td>${i.itemName}</td>
                            <td>${i.quantity}</td>
                            <td>${i.price} 원</td>
                            <td>
                                <button class="view" onclick="goProduct(${i.prd_idx})">view</button>
                                <button class="delete" onclick="deleteHeart(${i.prd_idx})">Delete</button>
                            </td>
                        </tr>
                    `
                }
    let heartBody = document.getElementById("heart_body");
    heartBody.innerHTML = html;
    })

}
function deleteCart(crt_idx){
    let data = {};
    data.crt_idx = crt_idx;
    httpUtil.defaultRequest('/member/cart/delete','post', data, (data) => {
        location.reload();
    })
}


function deleteHeart(prd_idx){
    let data = {};
    data.prd_idx = prd_idx;
    data.type = "heart"
    console.log(data);
    httpUtil.defaultRequest('/member/cartHeart/add','post', data, (data) => {
        location.reload();
    })
}

function goProduct(prd_idx) {
    location.href="/basic/items/"+prd_idx;
}