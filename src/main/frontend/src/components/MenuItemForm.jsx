import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import {createMenuItem, reset} from '../features/menuItems/menuItemSlice'
import Spinner from './Spinner'

function MenuItemForm() {
    const [formData, setFormData] = useState({
        name: '',
        description: '',
        imgUrl: '',
        price: '',
    })

    const dispatch = useDispatch()
    const navigate = useNavigate()

    const {name, description, imgUrl, price} = formData

    const { menuItem, isLoading, isError, isSuccess, message } = useSelector(
        (state) => state.menuItems
       );
     
       useEffect(() => {
           if(isError){
               toast.error(message)
           }
     
           if(isSuccess || menuItem){
               navigate('/')
             }
             dispatch(reset())
       }, [isError, isSuccess, message, menuItem, dispatch, navigate]);

    const onChange = (e) => {
        setFormData((prevState) => ({
            ...prevState,
            [e.target.name]: e.target.value
        }))
    }
    const onSubmit = (e) => {
        e.preventDefault();

      const menuItemData = {
        name,
        description,
        imgUrl,
        price,
      };

      dispatch(createMenuItem(menuItemData));
    }

    if(isLoading){
        return <Spinner/>
    }
  return (
    <>
      <section className="heading">
        <h1>
          Create Menu Item
        </h1>
      </section>
      <section className="form">
        <form onSubmit={onSubmit}>
          <div className="form-group">
            <input
              type="text"
              className="form-control"
              id="name"
              name="name"
              value={name}
              onChange={onChange}
              placeholder="Menu item Name"
              required
            />
          </div>
          <div className="form-group">
            <textarea
              rows='5'
              className="form-control"
              id="description"
              name="description"
              value={description}
              onChange={onChange}
              placeholder="Menu item Description"
              required
            />
          </div>
          <div className="form-group">
            <input
              type="text"
              className="form-control"
              id="imgUrl"
              name="imgUrl"
              value={imgUrl}
              onChange={onChange}
              placeholder="Menu item image URL"
              required
            />
          </div>
          <div className="form-group">
            <input
              type="number"
              className="form-control"
              id="price"
              name="price"
              value={price}
              onChange={onChange}
              placeholder="Menu item price"
              required
            />
          </div>
          <div className="form-group">
            <button className="btn btn-block">Submit</button>
          </div>
        </form>
      </section>
    </>
  );
}

export default MenuItemForm