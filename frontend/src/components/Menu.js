import React, { Component } from 'react';
import { Route,IndexRoute } from 'react-router-dom';
import Login from './Login/Login'
import SignUp from './SignUp/SignUp';
import Landingpage from './Landingpage/Landingpage';
import Profile from './Profile/Profile';
import JoinHackathon from './Hackathons/JoinHackathon';
import Payment from './Payment/Payment';
import submission from './submission/submission';
import Judge from './Judge/Judge';
import MembershipApprovals from './Membership/MembershipApprovals';
import CreateHackathons from './Hackathons/CreateHackathons';
import CreateOrganization from './Organizations/CreateOrganization';
import searchOrganizations from './Organizations/searchOrganizations'
import searchHackathons from './Hackathons/searchHackathons';
import Navbar from './Navbar/Navbar';
class Menu extends Component {
    constructor(props) {
        super(props);
        console.log("Inside Menu");
    }

    render() {
        return(
            <div>
                <Route  path="/" component={Navbar} />
                <Route  path="/login" component={Login} />
                <Route  path="/signup" component={SignUp} />
                <Route  path="/landingpage" component={Landingpage} />
                <Route  path="/profile" component={Profile} />
                <Route  path="/joinHackathon" component={JoinHackathon} />
                <Route  path="/payment" component={Payment} />
                <Route  path="/submission" component={submission} />
                <Route  path="/judge" component={Judge} />
                <Route  path="/membershipApprovals" component={MembershipApprovals} />
                <Route  path="/createHackathon" component={CreateHackathons} />
                <Route  path="/createOrg" component={CreateOrganization} />
                <Route  path="/searchOrgs" component={searchOrganizations} />
                <Route  path="/searchHackathons" component={searchHackathons} />

            </div>
        );
    }
}

export default Menu;