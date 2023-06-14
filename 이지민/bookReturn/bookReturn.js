

let getDataFunc = () => {
    let searchKey = document.getElementById("searchKey");
    let inputText = document.getElementById("search").value;
    fetch('./assets/data/borrow.json')
        .then(function(response) {
            return response.json();
        })
        .then((data) => {
            console.log(data);
            let filtedArray = data.borrow.filter((ele) => {
                if(searchKey.value === "도서번호"){
                    return ele.bookNumber.includes(inputText)
                }if(searchKey.value === "도서이름"){
                    return ele.bookName.includes(inputText)
                }
                else{
                    alert("미구현");
                }
            });
            
            const tableElement = document.getElementById('T');
            tableElement.innerHTML = tableElement.innerHTML.split("<tbody>")[1].split("</tbody>")[0];

            if(filtedArray.length  < 1){
                alert("검색된 데이터가 없다.");
                return 0;
            }
            // "bookNumber": "000001",
            // "memberNumber": "000001",
            // "bookName" : "이것이 자바다",
            // "returnState": "반납",
            // "borrowDate": "2023-06-13",
            // "returnDate": "2023-06-14",
            // "bookState": "1급"
            for(let i of filtedArray) {
                const trElement = document.createElement('tr');
                trElement.align = "center";
                trElement.classList.add('colContent');
                let td1= document.createElement('td');
                td1.innerHTML = i.bookNumber;
                let td2= document.createElement('td');
                td2.innerHTML = i.memberNumber;
                let td3= document.createElement('td');
                td3.innerHTML = i.bookName;
                let td4= document.createElement('td');
                td4.innerHTML = i.returnState;
                let td5= document.createElement('td')
                td5.innerHTML = i.borrowDate;
                let td6= document.createElement('td');
                td6.innerHTML = i.returnDate;
                let td7= document.createElement('td')
                td7.innerHTML = i.bookState;

                let td8= document.createElement('td');
                let a1= document.createElement('a')
                let button = document.createElement('button');
                button.classList.add('borrowButton');
                button.innerHTML = "반납";
                i.returnState !== "대여중" ? button.setAttribute("disabled", "disabled"): null;
                a1.href = "./returnModalDialog.html?" + i.bookNumber;
                a1.appendChild(button);
                td8.appendChild(a1);
                trElement.appendChild(td1);
                trElement.appendChild(td2);
                trElement.appendChild(td3);
                trElement.appendChild(td4);
                trElement.appendChild(td5);
                trElement.appendChild(td6);
                trElement.appendChild(td7);
                trElement.appendChild(td8);
                tableElement.appendChild(trElement);
            }
        })
        .catch(function(error) {
            console.log('Error:', error);
        });
}

let  searchButton = document.getElementById("searchButton");
searchButton.addEventListener("click", getDataFunc);