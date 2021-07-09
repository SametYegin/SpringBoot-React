import React, { Component } from 'react';
import UserSignupPage from './pages/UserSignupPage';
import { login } from './api/apiCalls';


class LoginPage extends Component {
    state = {
        email: null,
        password: null
    }

    onChange = event => {
        const {name , value} = event.target;
        this.setState({
            [name]: value
        })

    }

    onClickLogin = event => {
        event.preventDefault();
        const {email , password} = this.state;
        const creds = {
            email,
            password
        }
        const { push } = this.props.history;
        login(creds);
        push('/listall');
    }

    onClickAdminLogin = event => {
        event.preventDefault();
        const {email , password} = this.state;
        const creds = {
            username : email,
            password
        }
        const { push } = this.props.history;
        login(creds);
        push('/listall');
    }

    render() {
        return (
            <div>
                <form>
                    <h1 className = "text-center">Login Page</h1>
                    <div className = "form-group">
                    <label>Email</label>
                    <input className = "form-control" name="email" onChange={this.onChange}/>
                    </div>
                    <div className = "form-group">
                    <label>Password</label>
                    <input className = "form-control" name ="password" type = "password" onChange={this.onChange}/>
                    </div>
                    <div className = "text-center">
                    <button className = "btn btn-primary" onClick={this.onClickLogin}>Login</button>
                    </div>
                    <div className = "text-center">
                    <button className = "btn btn-primary" onClick={this.onClickAdminLogin}>Admin Login</button>    
                    </div>
                    
                </form>
                
            </div>
        );
    }
}

export default LoginPage;
