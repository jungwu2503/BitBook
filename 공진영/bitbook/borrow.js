
let getDataFunc = () => {
    let searchKey = document.getElementById("searchKey");
    let inputText = document.getElementById("search").value;
    fetch('./assets/data/book.json')
        .then(function(response) {
            return response.json();
        })
        .then((data) => {
            let filtedArray = data.book.filter((ele) => {
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
            for(let i of filtedArray) {
                const trElement = document.createElement('tr');
                trElement.align = "center";
                trElement.classList.add('colContent');
                let td1= document.createElement('td');
                td1.innerHTML = i.bookNumber;
                let td2= document.createElement('td');
                td2.innerHTML = i.bookName;
                let td3= document.createElement('td');
                td3.innerHTML = i.borrowState;
                let td4= document.createElement('td')
                td4.innerHTML = i.bookState;

                let td5= document.createElement('td');
                let a1= document.createElement('a')
                let button = document.createElement('button');
                button.classList.add('borrowButton');
                button.innerHTML = "대출";
                i.borrowState !== "대여가능" ? button.setAttribute("disabled", "disabled"): null;
                a1.href = "./burrowModalDialog.html?" + i.bookNumber;
                a1.appendChild(button);
                td5.appendChild(a1);
                trElement.appendChild(td1);
                trElement.appendChild(td2);
                trElement.appendChild(td3);
                trElement.appendChild(td4);
                trElement.appendChild(td5);
                tableElement.appendChild(trElement);
            }
        })
        .catch(function(error) {
            console.log('Error:', error);
        });
}

let  searchButton = document.getElementById("searchButton");
searchButton.addEventListener("click", getDataFunc);

        
        