import axios from "axios";

const API_URL = '/api/menu-items'

const getMenuItems = async () => {
    const response = await axios.get(API_URL)

    return response.data
}

const getMenuItem = async (menuItemId) => {
    const response = await axios.get(API_URL + `/${menuItemId}`)

    return response.data
}

const createMenuItem = async (menuItemData, token) => {
    const config = {
        headers: {
            Authorization: `Bearer ${token}`
        }
    }

    // const fd = new FormData()
    // fd.append("file", menuItemData.file, menuItemData.file)
    // fd.append("name", menuItemData.name)

    const response = await axios.post(API_URL, menuItemData, config)

    return response.data
}

const menuItemService = {
    getMenuItems,
    getMenuItem,
    createMenuItem,
}

export default menuItemService