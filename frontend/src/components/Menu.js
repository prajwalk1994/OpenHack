import React, { Component } from 'react';
import { Route } from 'react-router-dom';
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
                <Route exact={true} path="/profile" component={Profile} />
                <Route exact={true} path="/joinHackathon" component={JoinHackathon} />
                <Route exact={true} path="/payment" component={Payment} />
                <Route exact={true} path="/submission" component={submission} />
                <Route exact={true} path="/judge" component={Judge} />
                <Route exact={true} path="/membershipApprovals" component={MembershipApprovals} />
                <Route exact={true} path="/createHackathon" component={CreateHackathons} />
                <Route exact={true} path="/createOrg" component={CreateOrganization} />
                <Route exact={true} path="/searchOrgs" component={searchOrganizations} />
                <Route exact={true} path="/searchHackathons" component={searchHackathons} />

            </div>
        );
    }
}

export default Menu;