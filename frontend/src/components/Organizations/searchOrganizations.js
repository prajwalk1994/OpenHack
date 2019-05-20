import React, { Component } from 'react';
import url from '../../config/config'
import axios from 'axios';

class searchOrganizations extends Component {
    constructor(props) {
        super(props);
        this.state = {
            userid: localStorage.getItem("userid"),
            userEmail: localStorage.getItem("email"),
            organizationSearched: "EMPTY NAME",
            organizationSearchedId: "",
            organizationsList: [],
            // organizationsIds:[],
            organizationOwners: [],
            AllOrgsData: "",
            visibility: false
        }
    }

    componentDidMount = () => {
        //get all organizations list
        axios.get(url + "/organizations")
            .then((response) => {
                console.log(response.data)
                this.setState({
                    AllOrgsData: response.data
                })
                console.log(this.state.AllOrgsData[0].owner.email)
                var tempOrg = []
                // var tempOrgIds=[]
                var tempOwners = []
                for (let i of response.data) {
                    tempOrg.push(i.name)
                    tempOwners.push(i.owner.email)
                    // tempOrgIds.push(i.id)
                }
                this.setState({
                    organizationsList: tempOrg,
                    // organizationsIds:tempOrgIds
                    organizationOwners: tempOwners
                })
                console.log("state after setting orgs", this.state)
            })
            .catch((err) => {
                if (err.response) {
                    console.log("err", err.response)
                } else {
                    alert("something went wrong")
                }
            })
    }

    searchOrgnizations = () => {

    }

    sendJoinRequest = (e, organizationId) => {
        //send id of organization selected to backend
        e.preventDefault();
        console.log("sending join request...")
        axios.post(url + "/organizationMember/" + this.state.userid + "/" + organizationId)
            .then((response) => {
                console.log(response.data)
                alert("Join request sent!")
            })
            .catch((err) => {
                if (err.response) {
                    console.log("err", err.response)
                } else {
                    alert("something went wrong")
                }
            })
    }

    render() {
        var organizationsListDiv = "";
        organizationsListDiv = this.state.organizationsList.map((item, index) => {
            return (
                <div className="row">
                    <h5 className="col-sm-6 mt-4">{item}</h5>
                    {(this.state.organizationOwners[index]!=this.state.userEmail)?(<button className=" col-sm-6 form_element btn btn_login" onClick={(e) => this.sendJoinRequest(e, (index + 1))}>join</button>):(<button className=" col-sm-6 form_element btn btn-light" onClick={(e)=>{alert("Owner cannot join!")}}>Owner</button>)}
                </div>
            )
        })

        var datalistOrgs = "";
        datalistOrgs = this.state.organizationsList.map((item,index)=>{
            return(
                <div>
                    <option value={item}/>
                </div>
            )
        })

        var searchedOrgDiv = "";
        searchedOrgDiv = (
            <div className="row">
                <h4 className="col-sm-3" name="searchedName">{this.state.organizationSearched}</h4>
                <button className="col-sm-9  btn btn_login" onClick={this.sendJoinRequest}>join</button>
            </div>
        )

        return (
            <div className="row justify-content-center">
                <div className=" justify-content-center formContainer">
                    <h3>Search Organizations</h3>
                    <div className="row justify-content-center">
                        <input list="organizations" type="text" className="form-control mb-4" name="searchOrg" placeholder="search organizations"></input>
                        <datalist id="organizations">
                            {datalistOrgs}
                        </datalist>
                    </div>
                    <div className="row justify-content-center">
                        <button className="form_element btn btn_login" onClick={this.searchOrgnizations}>Search</button>
                    </div>

                </div>
                <div className="justify-content-center formContainer ml-5">
                    {!this.state.visibility ? <h3>List of all Organizations</h3> : <div></div>}
                    <div className="">
                        {!this.state.visibility ? organizationsListDiv : searchedOrgDiv}
                    </div>
                </div>
            </div>
        );
    }
}

export default searchOrganizations;