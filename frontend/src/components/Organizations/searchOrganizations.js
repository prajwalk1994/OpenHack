import React, { Component } from 'react';

class searchOrganizations extends Component {
    constructor(props) {
        super(props);
        this.state = {
            organizationSearched: "",
            visibility: false
        }
    }

    componentDidMount = () => {
        //get all organizations list
    }

    searchOrgnizations = () => {
        
    }

    sendJoinRequest = () => {
        //send id of organization selected to backend
    }

    render() {
        return (
            <div>
                <h3>Search Organizations</h3>
                <div>
                    <div className="row justify-content-center">
                        <div className="col-sm-4 ">
                            <input type="text" className="form-control mb-4" name="searchOrg" placeholder="search organizations"></input>
                            <button className="btn btn-primary" onClick={this.searchOrgnizations}>Search</button>                            </div>
                    </div>
                    { !this.state.visibility ? <div/> : <div className="row justify-content-center">
                        <div className="col-sm-4 ">
                            <label className="mb-4 mr-3" name="searchedName">{this.state.organizationSearched}</label>
                            <button className="btn btn-primary" onClick={this.sendJoinRequest}>join</button>
                        </div>
                    </div> }
                    
                </div>
            </div>
        );
    }
}

export default searchOrganizations;