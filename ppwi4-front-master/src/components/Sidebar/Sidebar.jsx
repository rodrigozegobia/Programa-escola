import React from "react";
import ShowItem from "./ShowItem";
import SidebarData from "./SidebarData";

const Sidebar = ({isToggle}) => {
    return (
        <div>
            <div className={isToggle ? "app-sidebar" : "app-sidebar inactive"}>
                <ul>
                    {
                        SidebarData &&
                        SidebarData.map((item, index) => {
                            return (
                                <ShowItem path={item.path} icon={item.icon} page={item.page} index={index} key={index} />
                            )
                        })
                    }
                </ul>
            </div>
        </div>
    )
}

export default Sidebar;