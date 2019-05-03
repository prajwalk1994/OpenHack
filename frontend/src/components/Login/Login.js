import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import './Login.css'
import Navbar from '../Navbar/Navbar';

class Login extends Component {
    constructor(props) {
        super(props);
        console.log("Inside Login");
    }
    render() {
        return (
            <div>
                <div id="home" class="bg_div">
                    <center>
                        <div class="loginHeader" >
                            {/* <Alert bsStyle="warning"></Alert> */}
                            <h2>Log in to OpenHack</h2>
                            <p className="">Need an account? <a href="#"><Link to="/SignUp"><span>Sign Up</span></Link></a></p>
                        </div>
                        <div className="container intro">
                            <div class="formContainer col-lg-4 col-lg-offset-4 col-md-5 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-12">
                                <div class="formHeading">
                                    <h3>Account Login</h3>
                                </div>
                                <div class="form-group">
                                    <form>
                                        <input class="form-control form_element" onChange="{this.handleChange.bind(this)}" name="email" value="{email}" type="text" placeholder="Email address"></input>
                                        <input class="form-control form_element" onChange="{this.handleChange.bind(this)}" name="password" value="{password}" type="password" placeholder="password"></input>
                                        <div class="form-group" className="row">
                                            <div className="col-sm-3 align-middle">
                                                <label for="role">Role</label>
                                            </div>
                                            <div className="col-sm-9">
                                                <select class="form-control" id="role">
                                                    <option>Hacker</option>
                                                    <option>Judge</option>
                                                    <option>Admin</option>
                                                </select>
                                            </div>
                                        </div>
                                        {/* <a class="float_left" href="#">Forgot Password?</a> */}
                                        <br></br>
                                        <button onClick="{this.handleLogin.bind(this)}" class="form_element btn_login btn btn-lg btn-block" type="submit">Log In</button>
                                        <div class="social_login">
                                            <button class="form_element btn_fb btn btn-lg btn-block" >Log in with Facebook</button>
                                            <button class="form_element btn_google btn btn-lg btn-block">Log in with Google</button>
                                        </div>
                                        <div class="">
                                            <label class="form_footer">We don't post anything without your permission.</label>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </center>
                </div>
            </div>
        );
    }
}

export default Login;