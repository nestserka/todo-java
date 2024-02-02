let modalWindow;

const deleteTask = (id) => {
    const url = `/delete/${id}`;
    fetch(url, {
        method: 'DELETE',
    })
        .then(res => {
            return res.text();
        })
        .then(res => {
            location.reload();
            console.log(res);
        })
        .catch(error => console.error('Error:', error));
}

const updateData = (taskID, initialDescription, initialStatus) => {
    const url = `/todo/${taskID}`;
    
    const data = {
        description: initialDescription,
        status: initialStatus
    };

    return fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            throw error;
        });
}

const editTask = (taskId, description, status) => {
    console.log("click" + taskId);
    openModal(taskId, description, status);
}

function closeWindow() {
    modalWindow.classList.add("modal_hide");
    setTimeout(function () {
      modalWindow.classList.remove("modal_visibility");
      modalWindow.classList.remove("modal_hide");
      modalWindow.innerHTML = "";
    }, 500);
}

const saveEditedTask = (taskID, initialDescription, initialStatus) =>{
    updateData(taskID, initialDescription, initialStatus)
        .then(() => {
            closeWindow();
        })
        .catch(error => {
            console.error("Error updating data:", error);
        });
}

const setSaveButton = (taskID, description, status) => {
    const save = document.querySelector(".btn-save");
    let updatedDescription = description;
    let updatedStatus = status;

    const modalDescriptionInput = document.getElementById("modalDescription");
    const modalStatusSelect = document.getElementById("modalStatus");

    modalDescriptionInput.addEventListener("input", function() {

        updatedDescription = this.value;
    });

    modalStatusSelect.addEventListener("change", function() {
        updatedStatus = this.value;
    });
    save.addEventListener('click', function() {
        saveEditedTask(taskID,  updatedDescription, updatedStatus);
    });
}

function openModal(taskId, description, status) {
    modalWindow = document.getElementById("modal");
    const modalBody = `
    <div class="modal__popup">
    <div class="modal-wrapper">
        <p>Task:</p>
        <input type="text" name="description" id="modalDescription" value="${description}">
        <p>Status:</p>
        <select name="status" id="modalStatus">
            <option value="0" ${status === "IN_PROGRESS" ? "selected" : ""}>In Progress</option>
            <option value="1" ${status === "DONE" ? "selected" : ""}>Done</option>
            <option value="2" ${status === "PAUSED" ? "selected" : ""}>Paused</option>
        </select>
        <button class="button btn-save">Save</button>
    </div>
</div>
    `;
    modalWindow.insertAdjacentHTML("beforeEnd", modalBody);
    console.log(taskId)
    setSaveButton(taskId, description, status);
    openModelWindow()
}

function openModelWindow() {
    modalWindow.classList.add("modal_visibility");
    const btn = document.querySelector(".btn-close__modal");
    modalWindow.addEventListener("click", closeModal);
  }


  function closeModal() {
    if (event.target === modalWindow) {
      modalWindow.classList.add("modal_hide");
      setTimeout(function () {
        modalWindow.classList.remove("modal_visibility");
        modalWindow.classList.remove("modal_hide");
        modalWindow.innerHTML = "";
      }, 300);
    }
  }

function closeWindow() {
    modalWindow.classList.add("modal_hide");
    setTimeout(function () {
        modalWindow.classList.remove("modal_visibility");
        modalWindow.classList.remove("modal_hide");
        modalWindow.innerHTML = "";
    }, 500);
    location.reload();
}