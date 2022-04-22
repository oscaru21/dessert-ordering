import axios from "axios";

const API_URL = '/api/menu-items'

const getItemReviews = async (menuItemId) => {
    const response = await axios.get(API_URL + `/${menuItemId}` + '/item-reviews')

    return response.data
}

const createItemReview = async (menuItemId, text, token) => {
    const config = {
        headers: {
            Authorization: `Bearer ${token}`
        }
    }
    const response = await axios.post(API_URL + `/${menuItemId}` + '/item-reviews', {text}, config)

    return response.data
}

const itemReviewService = {
    getItemReviews,
    createItemReview,
}

export default itemReviewService