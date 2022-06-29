
const avatarEL = document.getElementById("avatar");
const avatarPreviewEl = document.getElementById("avatar-preview");
const API_URL = "http://localhost:8080/api/v1";
// upload file
let id = 1
// upload file
const uploadFileAPI = file => {
    const formData = new FormData();

    formData.append("file",file)

    return axios.post(`${API_URL}/upload-file/${id}`,formData)
}

avatarEL.addEventListener('change', async function (event) {
    try {
        // lấy file cần upload
        let file = event.target.files[0];
        console.log(event.target.files)

        let res = await uploadFileAPI(file)

        console.log(res)

        avatarPreviewEl.src = `http://localhost:8080${res.data}`
    } catch (error) {
        console.log(error)
    }

})


