`use strict`;

const ListName = document.querySelector("#name");
const Description = document.querySelector("#description");
const DateCreated = document.querySelector("#dateCreated");
const DeadlineDate = document.querySelector("#deadlineDate");
const Completed = document.querySelector("#Completion");
const ListId = document.querySelector("#ListID");


const createList = () => {
    const ListName = ListName.value;

    let data = {
        ListName: ListName,
    }
    fetch("#", {
        method: "POST",
        body: JSON.stringify(data),
        headers: { "Content-Type": "application/json" }
    })
        .then(response => response.json())
        .then(info => {
            console.log(info);
        })
        .catch(err => console.error(`Error ${err}`));
}

const createToDo = () => {
    const Description = Description.value;
    const DateCreated = DateCreated.value;
    const DeadlineDate = DeadlineDate.value;
    const Completed = Completed.value;
    const ListId = ListId.value;

    let data = {
        ListName: ListName,
    }
    fetch("#", {
        method: "POST",
        body: JSON.stringify(data),
        headers: { "Content-Type": "application/json" }
    })
        .then(response => response.json())
        .then(info => {
            console.log(info);
        })
        .catch(err => console.error(`Error ${err}`));
}



