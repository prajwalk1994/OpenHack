import React, { Component } from 'react';
import '../Navbar/Navbar2.css'
import firebase from 'firebase'
import { Redirect } from 'react-router-dom';
class Navbar2 extends Component {
    constructor(props) {
        super(props);
        this.state = {
            redirect: ""
        }
    }

    componentDidMount=async ()=>{
        await this.setState({
            redirect:""
        })
    }

    handleLogout = async (e) => {
        e.preventDefault();
        localStorage.removeItem("userid")
        localStorage.removeItem("role")
        localStorage.removeItem("email")
        localStorage.removeItem("username")
        localStorage.removeItem("verified")
        firebase.auth().signOut()
        localStorage.removeItem("firebaseEmail")
        
        await this.setState({
            redirect:<Redirect to="/login"/>
        })
        await window.location.reload()
    }
    render() {
        return (
            <div>
                {this.state.redirect}
                <div class="nav-wrapper">
                    <div class="logo-container">
                        <h2>Open Hack</h2>
                    </div>
                    <nav>
                        <input class="hidden" type="checkbox" id="menuToggle" />
                        <label class="menu-btn" for="menuToggle">
                            <div class="menu"></div>
                            <div class="menu"></div>
                            <div class="menu"></div>
                        </label>
                        <div class="nav-container">
                            <ul class="nav-tabs">
                                <a href="/"><li class="nav-tab">Home</li></a>
                                <a href="/login"><li class="nav-tab">Login</li></a>
                                <a href="/profile"><li class="nav-tab">Profile</li></a>
                                <a href="/searchHackathons"><li class="nav-tab">Hackathons</li></a>
                                <a href="/searchOrgs"><li class="nav-tab">Organizations</li></a>
                                <a href="/resultsreport"><li class="nav-tab">Result Report</li></a>
                                <a href="/financialreport"><li class="nav-tab">Financial Report</li></a>
                                <a href="/earningreport"><li class="nav-tab">Earnings Report</li></a>
                                <a href="/logout" onClick={this.handleLogout}><li class="nav-tab">Logout</li></a>
                            </ul>
                        </div>
                    </nav>
                </div>


                {/* <div class="hero">
                    <div class="container">
                        <nav class="navbar navbar-expand-lg navbar-dark">
                            <a class="navbar-brand" href="#"><h1>Open Hack</h1></a>
                            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                                <span class="navbar-toggler-icon"></span>
                            </button>
                            <div class="collapse navbar-collapse" id="navbarCollapse">
                                <ul class="navbar-nav mr-auto">
                                    <li class="nav-item active">
                                        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#">About</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#">Gallery</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#">Blog</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="#">Contact</a>
                                    </li>
                                </ul>

                            </div>
                        </nav>
                    </div>
                </div> */}
            </div>
        );
    }
}

export default Navbar2;