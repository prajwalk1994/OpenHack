import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css';
import '../../fontawesome/css/all.css';

class Navbar extends Component {
    render() {
        return (
            <div>
                <nav className="navbar navbar-expand-lg navbar-light">
                    <div className="container">
                        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
                            <span className="navbar-toggler-icon"></span>
                        </button>
                       
                        <div className="collapse navbar-collapse" id="navbarTogglerDemo03">
                            <div class="borderRight  ml-auto mt-2 mt-lg-0">
                                <ul className="navbar-nav">
                                    <li className="nav-item active">
                                        <a className="nav-link" href="/profile"><i class="fal fa-home fa-lg iconColour"></i><br />
                                        <span class="nav-icon-text">Home</span><span class="sr-only">(current)</span></a>
                                    </li>
                                    {/* <li className="nav-item">
                                        <a className="nav-link" href="/login"><i class="fal fa-users fa-lg iconColour">
                                            </i><br />
                                            <span class="nav-icon-text">login</span></a>
                                    </li> */}
                                    {/*
                                    <li className="nav-item">
                                        <a className="nav-link" onClick={() => { this.jobsDisplay() }}><i class="fal fa-suitcase fa-lg iconColour"></i><br />
                                        <span class="nav-icon-text">Jobs</span></a>
                                    </li>
                                    <Link to='/messages'>
                                        <li className="nav-item">
                                            <a className="nav-link" href="#"><i class="fal fa-envelope fa-lg iconColour"></i><br />
                                                <span class="nav-icon-text">Messaging</span>
                                            </a>
                                        </li>
                                    </Link>
                                    <li className="nav-item">
                                        <a className="nav-link" href="#"><i class="fal fa-bell fa-lg iconColour">
                                            
                                            </i><br />
                                            <span class="nav-icon-text">Notifications </span></a>
                                    </li> */}
                                    <li className="nav-item" onClick={this.openProfile}>
                                        <a className="nav-link" ><i class="fa fa-user fa-lg iconColour"></i><br />
                                            <span class="nav-icon-text">Me</span></a>
                                    </li>
                                    <li className="nav-item" onClick={this.logout}>
                                        <a className="nav-link" href="/login"><i class="fal fa-sign-out fa-lg iconColour"></i><br />
                                            <span class="nav-icon-text">SignOut</span></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </nav>
                
            </div>
        );
    }
}

export default Navbar;