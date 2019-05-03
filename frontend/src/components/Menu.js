import React, { Component } from 'react';
import { Route } from 'react-router-dom';
import Login from './Login/Login'
import SignUp from './SignUp/SignUp';
import Landingpage from './Landingpage/Landingpage';
class Menu extends Component {
    constructor(props) {
        super(props);
        console.log("Inside Menu");
    }

    render() {
        return(
            <div>
                <Route exact={true} path="/" component={Login} />
                <Route exact={true} path="/signup" component={SignUp} />
                <Route exact={true} path="/landingpage" component={Landingpage} />
            </div>
        );
    }
}

export default Menu;