import axios from "axios";

const API_URL = '/api/orders/'

const createOrder = async (orderData, token) => {
    const {address, note, cartItems} = orderData

    const config = {
        headers: {
            Authorization: `Bearer ${token}`
        }
    }

    const order = await axios.post(API_URL, {address, note}, config)

    const promises = cartItems.map(async item => {
        const {data} = await createOrderItem(order.data.id, item, token)
        console.log(data)
    })

    await Promise.all(promises)

    const response = await getOrder(order.data.id, token)
    
    return response
}

const getOrder = async (orderId, token) => {
    const config = {
        headers: {
            Authorization: `Bearer ${token}`
        }
    }
    const response = await axios.get(API_URL + orderId, config)

    return response.data
}

const createOrderItem = async (orderId, cartItem, token) => {
    const orderItem = {
        quantity: cartItem.qty,
        menuItemId: cartItem.id,
        orderId,
    }
    const config = {
        headers: {
            Authorization: `Bearer ${token}`
        }
    }

    const response = await axios.post(API_URL + orderId, orderItem, config)
    
    return response
}

const cartService = {
    createOrder,
}

export default cartService