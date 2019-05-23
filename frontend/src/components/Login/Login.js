import React, { Component } from 'react';
import './Login.css'
import url from '../../config/config'
import axios from 'axios';
import { Link, Redirect } from "react-router-dom";
import firebase from 'firebase'
import firebaseui from 'firebaseui';
import StyledFirebaseAuth from 'react-firebaseui/StyledFirebaseAuth'
import { setTimeout } from 'timers';

firebase.initializeApp({
    apiKey: "AIzaSyC0PdhMUGu5E2IsSuaLkJ_4V0VOJXWoaIM",
    authDomain: "openhack-afe32.firebaseapp.com"
})
class Login extends Component {
    constructor(props) {
        super(props);
        console.log("Inside Login");
        this.state = {
            email: "",
            password: "",
            username: "",
            // check: "no",
            firebaseuser: {},
            isSignedIn: false
        }
        this.uiConfig = {
            signInFlow: "popup",
            // 'signInSuccessUrl': `profile`,
            //   'credentialHelper': firebaseui.auth.CredentialHelper.NONE,
            //   'callbacks': {
            //     'signInSuccess': function(currentUser, credential, redirectUrl) {
            //       console.log('callback run: ');
            //       alert("Signed in as "+currentUser + " with credential " + credential);
            //       return true;
            //     }.bind(this),
            //     uiShown: function() {
            //       alert("doing login stuff.");
            //     }
            //   },
            signInOptions: [
                firebase.auth.GoogleAuthProvider.PROVIDER_ID,
                firebase.auth.FacebookAuthProvider.PROVIDER_ID,
            ],
            callbacks: {

                signInSuccess: async () => {
                    // alert("ksai")
                    await setTimeout(() => {

                    }, 3000);
                    let sendingObj = await {
                        // ...this.state.firebaseuser,
                        email: localStorage.getItem("firebaseEmail"),
                        password: "123",
                        role: "Hacker",
                        username: localStorage.getItem("firebaseEmail")
                    }
                    console.log(sendingObj)
                    await axios.post(url + "/login", sendingObj).then(async (res) => {
                        localStorage.setItem("userid", res.data[0].id)
                        localStorage.setItem("role", res.data[0].role)
                        localStorage.setItem("email", res.data[0].email)
                        localStorage.setItem("username", res.data[0].username)
                        localStorage.setItem("verified", res.data[0].verified)

                        console.log(res.data)
                        console.log("Login successful ", res.status)
                        alert("Login successful")
                        await this.setState({
                            redirectVar: <Redirect to="/profile" />
                        })

                    }).catch(async (err) => {
                        // console.log(err)
                        console.log("Invalid credentials!")
                        await axios.post(url + "/signUp", sendingObj).then(async (res) => {
                            console.log(res)
                            await localStorage.setItem("userid", res.data.id)
                            await localStorage.setItem("role", res.data.role)
                            await localStorage.setItem("email", res.data.email)
                            await localStorage.setItem("username", res.data.username)
                            await localStorage.setItem("verified", res.data.verified)
                            alert("Signup successful")

                            await this.setState({
                                redirectVar: <Redirect to="/profile" />
                            })


                        }).catch(err => {
                            if (err.response) {
                                alert(err.response.data)
                            }
                            return false;
                            console.log(err)
                        })
                    })
                    return false;
                }
            }
        }

    }


    //      // Make sure we un-register Firebase observers when the component unmounts.
    //   componentWillUnmount() {
    //     this.unregisterAuthObserver();
    //   }

    componentDidMount = async () => {
        console.log(this.state)
        var check = false
        localStorage.setItem("firebaseEmail", "")
        await firebase.auth().onAuthStateChanged(async user => {

            if (!!user) {
                // check=true
                // await this.setState({ isSignedIn: !!user,
                // check:"yes" })
                // let firebaseuser = await {
                //     email: user.email,
                //     password: "123",
                //     role: "Hacker",
                //     username: user.email,
                //     // profilepic:user.photoURL,
                //     // firstname:user.displayName,
                //     // phonenumber:user.phoneNumber
                // }
                localStorage.setItem("firebaseEmail", user.email)
                // await this.setState({
                //     firebaseuser:firebaseuser
                // })

                // console.log("user", firebaseuser)
                console.log(this.state)
                setTimeout(() => {

                }, 3000);
                await this.setState({ isSignedIn: !!user })
            }
            // console.log(this.state)

        })
        // if (check) {
        //     console.log("here")
        //     if (!!this.state.isSignedIn) {

        //         await axios.post(url + "/login", this.state.firebaseuser).then(async (res) => {
        //             localStorage.setItem("userid", res.data[0].id)
        //             localStorage.setItem("role", res.data[0].role)
        //             localStorage.setItem("email", res.data[0].email)
        //             localStorage.setItem("username", res.data[0].username)
        //             localStorage.setItem("verified", res.data[0].verified)

        //             console.log(res.data)
        //             console.log("Login successful ", res.status)
        //             alert("Login successful")
        //             await this.setState({
        //                 redirectVar: <Redirect to="/profile" />
        //             })

        //         }).catch(async (err) => {
        //             // console.log(err)
        //             // alert("Invalid credentials!")
        //             await axios.post(url + "/signUp", this.state.firebaseuser).then(async (res) => {
        //                 console.log(res)
        //                 if (res.data == "Success!") {
        //                     alert("Signup successful")
        //                     await this.setState({
        //                         redirectVar: <Redirect to="/profile" />
        //                     })
        //                 }
        //                 else {
        //                     alert(res.data)
        //                 }

        //             }).catch(err => {
        //                 console.log(err)
        //             })
        //         })
        //     }

        //     // console.log("user",JSON.stringify(user))
        // }
    }
    handleChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }
    handleLogin = (e) => {
        e.preventDefault();
        const user = {
            ...this.state
        }

        axios.post(url + "/login", user).then(async (res) => {
            localStorage.setItem("userid", res.data[0].id)
            localStorage.setItem("role", res.data[0].role)
            localStorage.setItem("email", res.data[0].email)
            localStorage.setItem("username", res.data[0].username)
            localStorage.setItem("verified", res.data[0].verified)

            console.log(res.data)
            console.log("Login successful ", res.status)
            await this.setState({
                redirectVar: <Redirect to="/profile" />
            })

        }).catch((err) => {
            // console.log(err)
            alert("Invalid credentials!")
        })
    }

    // fauth = () => {
    //     alert("yes")
    //     firebase.auth().signOut()
    //     this.setState({
    //         check: "yes"
    //     })
    // }

    render() {
        return (
            <div>
                <div>
                    {this.state.redirectVar}
                </div>
                <div id="home" class="">
                    <center>
                        <div class="loginHeader" >
                            <h2>Log in to OpenHack</h2>
                            <p className="">Need an account? <a href="#"><Link to="/SignUp"><span className="orangeColor"><u>Sign Up</u></span></Link></a></p>
                        </div>
                        <div className="container intro">
                            <div class="formContainer col-lg-4 col-lg-offset-4 col-md-5 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-12">
                                <div class="formHeading">
                                    <h3>Account Login</h3>
                                </div>
                                <div class="form-group">
                                    <form>
                                        <input class="form-control form_element" onChange={this.handleChange.bind(this)} type="email" name="email" value={this.state.email} type="text" placeholder="Email address"></input>
                                        <input class="form-control form_element" onChange={this.handleChange.bind(this)} name="password" value={this.state.password} type="Password" placeholder="password"></input>
                                        <br></br>
                                        <button onClick={this.handleLogin.bind(this)} class="form_element btn_login btn btn-lg btn-block" type="submit">Log In</button>
                                        {/* <div class="social_login">
                                            <button class="form_element btn_fb btn btn-lg btn-block" >Log in with Facebook</button>
                                            <button class="form_element btn_google btn btn-lg btn-block">Log in with Google</button>
                                        </div> */}
                                        <StyledFirebaseAuth
                                            uiConfig={this.uiConfig}
                                            firebaseAuth={firebase.auth()} />
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