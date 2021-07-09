import React from 'react';
import { signup } from '../api/apiCalls';

class UserSignupPage extends React.Component{

    state = {
        username: null,
        email: null,
        password: null,
        education: null,
        workexp: null,
        pendingApiCall: false,
        errors: {
            
        }
    };

    onChange = event => {
        const value = event.target.value;
        const name = event.target.name;
        this.setState({
            [name]: value

        });
    };

    onClickSignup = async event => {
        event.preventDefault();

        const {username , email , password, education, workexp} = this.state;

        const body = {
            username,
            email,
            password,
            education,
            workexp 


        };
        this.setState({pendingApiCall: true});

        try{
        const response  = await signup(body);
        }catch(error){
            

        }
        this.setState({pendingApiCall: false});


      
    };

    

    

    render(){

        
        
        return(
            <div className="container">
            <form>
                <h1 className = "text-center">Sign Up</h1>
                <div className = "form-group">
                <label>Username</label>
                <input className ="form-control" name= "username" onChange={this.onChange}/>
                
                </div>
                <div className = "form-group">
                <label>Email</label>
                <input  className= "form-control" name = "email" onChange={this.onChange}/>
                
                </div>
                <div className = "form-group">
                <label>Password</label>
                <input className= "form-control" name = "password" type="password" onChange={this.onChange}/>
                </div>
                <div className = "form-group">
                <label>Education</label>
                <input className= "form-control" name = "education" onChange={this.onChange}/>
                </div>
                <div className = "form-group">
                <label>Work Experience</label>
                <input className= "form-control" name = "workexp" onChange={this.onChange}/>
                </div>
                
                <div className = "text-center">
                 <button className = "btn btn-primary" onClick={this.onClickSignup} disabled={this.state.pendingApiCall}>Sign Up</button>
                 </div>


            </form>
            </div>
            

        );
    }

}

export default UserSignupPage;