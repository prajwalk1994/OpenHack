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
                        <a className="navbar-brand" href="/newsFeed"><img className="logo" src="data:image/png;base64,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" /></a>
                        <form className="form-inline my-2 my-lg-0">
                            <input className="form-control mr-sm-2 fontAwesome iconColour" type="search" onChange={this.onChange} name="searchString" placeholder="&#xF002; Search" aria-label="Search" />
                            <button className="btn btn-outline-light my-2 my-sm-0 iconColour" onClick={this.searchUser} type="button">Search</button>
                        </form>
                        <div className="collapse navbar-collapse" id="navbarTogglerDemo03">
                            <div class="borderRight  ml-auto mt-2 mt-lg-0">
                                <ul className="navbar-nav">
                                    <li className="nav-item active">
                                        <a className="nav-link" href="/profile"><i class="fal fa-home fa-lg iconColour"></i><br />
                                        <span class="nav-icon-text">Home</span><span class="sr-only">(current)</span></a>
                                    </li>
                                    <li className="nav-item">
                                        <a className="nav-link" href="/connections"><i class="fal fa-users fa-lg iconColour">
                                            {/* <Badge count={this.state.acceptedConnections.length} >
                                                <a href="/connections" className="head-example" />
                                            </Badge> */}
                                            </i><br />
                                            <span class="nav-icon-text">My Network</span></a>
                                    </li>
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
                                            {/* <Badge count={this.state.acceptedConnections.length} >
                                                <a href="/connections" className="head-example" />
                                            </Badge> */}
                                            </i><br />
                                            <span class="nav-icon-text">Notifications </span></a>
                                    </li>
                                    <li className="nav-item" onClick={this.openProfile}>
                                        <a className="nav-link" ><i class="fa fa-user fa-lg iconColour"></i><br />
                                            <span class="nav-icon-text">Me</span></a>
                                    </li>
                                    <li className="nav-item" onClick={this.logout}>
                                        <a className="nav-link"><i class="fal fa-sign-out fa-lg iconColour"></i><br />
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