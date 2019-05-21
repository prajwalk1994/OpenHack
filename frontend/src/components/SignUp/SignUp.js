import React, { Component } from 'react';
import '../SignUp/SignUp.css';
import Axios from 'axios';
import url from '../../config/config'
import { Link, Redirect } from "react-router-dom";

class SignUp extends Component {
    constructor(props) {
        super(props);
        console.log("Inside SignUp");

        this.state = {
            name: "",
            username: "",
            email: "",
            password: "",
            role: "Hacker",
            redirectVar: ""
        }
    }

    handleChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }
    callingBackend = async (user) => {
        var re = /^([0-9a-zA-Z]){3,}$/;
        if(!re.test(this.state.username)){
            alert("Username/ScreenName must be alphanumeric and must be atleast 3 characters")
        }
        else{
            await Axios.post(url + "/signUp", user).then(async (res) => {
                console.log(res)
                if (res.data == "Success!") {
                    console.log("Signup successful")
                    await this.setState({
                        redirectVar: <Redirect to="/login" />
                    })
                }
                else {
                    alert(res.data)
                }

        }).catch(err => {
            console.log(err)
        })
        }
        
    }
    handleClick = (e) => {
        e.preventDefault();
        console.log(this.state)
        let re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if (!re.test(this.state.email)) {
            alert("Invalid Email Format!")
        }
        else {
            const user = {
                ...this.state
            }
            if (this.state.email.endsWith("@sjsu.edu") || this.state.role == "Admin") {
                if (this.state.role == "Admin" && this.state.email.endsWith("@sjsu.edu")) {
                    this.callingBackend(user)
                }
                else {
                    alert("Incompatible role")
                }
            }
            else {
                this.callingBackend(user)
            }
        }


    }




    render() {

        return (
            <div className="container">
                <center>
                    <div>
                        {this.state.redirectVar}
                    </div>
                    <div className="mt-5" >
                        <div class="container formContainer col-lg-4 col-lg-offset-4 col-md-5 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-12">
                            <div class="form-group">
                                <div>
                                    <h2 className="">Open Hack Sign Up</h2>
                                    <label>Already existing user ? <a href="/login">click here</a></label>
                                </div>
                                <form>
                                    <div>
                                        <input class="form-control  form_element" name="name" type="text" onChange={this.handleChange.bind(this)} placeholder="Name"></input>
                                        <input class="form-control  form_element" name="username" type="text" onChange={this.handleChange.bind(this)} placeholder="Screen Name"></input>
                                    </div>
                                    <input class="form-control form_element" type="email" name="email" onChange={this.handleChange.bind(this)} placeholder="Email address"></input>
                                    <input class="form-control form_element" type="password" name="password" onChange={this.handleChange.bind(this)} placeholder="password"></input>
                                    <div class="form-group" className="row">
                                        <div className="col-sm-3 align-middle">
                                            <label for="role">Role</label>
                                        </div>
                                        <div className="col-sm-9">
                                            <select class="form-control" onChange={this.handleChange} name="role" >
                                                <option>Hacker</option>
                                                <option>Judge</option>
                                                <option>Admin</option>
                                            </select>
                                        </div>
                                    </div>
                                    <button class="form_element btn_login btn btn-lg btn-block" onClick={this.handleClick.bind(this)} type="submit">Sign Me Up</button>
                                    <div class="or">
                                    </div>
                                    <div class="">
                                        <label class="form_footer">We don't post anything without your permission.</label>
                                    </div>
                                    <div class="">
                                        <label class="form_footer">By creating an account you are accepting our Terms and Conditions and Privacy Policy.
                                        </label>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </center>

            </div>
        );
    }
}

export default SignUp;