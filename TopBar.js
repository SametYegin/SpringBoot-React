import React, { Component } from 'react';
import { Link } from 'react-router-dom';

class TopBar extends Component {
    render() {
        return (
            <nav className="navbar navbar-light bg-light">
                <Link className="navbar=brand" to="/">
                    Resume Project
                </Link>

                <ul className="navbar-nav ">
                    <li>
                        <Link to="/login">
                        Login
                        </Link>

                    </li>
                    <li>
                        <Link to="/signup">
                        Signup
                        </Link>

                    </li>

                </ul>
            </nav>
        );
    }
}

export default TopBar;