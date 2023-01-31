import { Navigate, Outlet } from 'react-router-dom'
import {useAuthStatus} from '../hooks/useAuthStatus'
import Spinner from './Spinner'

const AdminRoute = () => {
    const {isAdmin, isLoading} = useAuthStatus()

    if(isLoading){
      return <Spinner />
    }

  return isAdmin ? <Outlet /> : <Navigate to='/' />
}

export default AdminRoute