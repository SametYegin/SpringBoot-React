import React, { useEffect, useState } from 'react';
import ProfileCard from '../components/ProfileCard';
import { Container } from 'react-bootstrap';
import { getUser } from '../api/apiCalls';

const UserPage = props => {

    const [user , setUser] = useState({});

    useEffect(() => {
        const loadUser = async () =>{
            try{
            const response = await getUser(props.match.params.email)
            setUser(response.data)
            }catch (error){
    
            }
        }
        loadUser();
    }, [props.match.params.email] );

    
    return (
        <div className = "container">
            <ProfileCard user = {user}/>
            
        </div>
    );
};

export default UserPage;