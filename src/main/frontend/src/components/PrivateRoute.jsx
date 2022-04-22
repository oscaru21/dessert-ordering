import { Navigate, Outlet } from 'react-router-dom'
import {useAuthStatus} from '../hooks/useAuthStatus'
import Spinner from './Spinner'

const PrivateRoute = () => {
    const {isloggedIn, isLoading} = useAuthStatus()

    if(isLoading){
      return <Spinner />
    }

  return isloggedIn ? <Outlet /> : <Navigate to='/' />
}

export default PrivateRoute